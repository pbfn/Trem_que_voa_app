package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.usecase.GetLocalUserUseCase
import com.br.ioasys.tremquevoa.domain.usecase.UpdateEmergencyContactsUserUseCase
import com.br.ioasys.tremquevoa.domain.usecase.UpdateUserUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class UpdateUserViewModel(
    private val updateEmergencyContactsUserUseCase: UpdateEmergencyContactsUserUseCase,
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {

    private var _responseUpdateEmergencyContact = MutableLiveData<ViewState<Boolean>>()
    var responseUpdateEmergencyContact: LiveData<ViewState<Boolean>> =
        _responseUpdateEmergencyContact


    fun updateEmergencyContact(token: String, emergencyName: String, emergencyPhone: String) {
        _responseUpdateEmergencyContact.postLoading()
        updateEmergencyContactsUserUseCase(
            params = UpdateEmergencyContactsUserUseCase.Params(
                token = token,
                emergencyName = emergencyName,
                emergencyPhone = emergencyPhone
            ),
            onSuccess = { userResponse ->
                getLocalUser(userResponse)
            },
            onError = {
                _responseUpdateEmergencyContact.postError(it)
            }

        )
    }

    private fun getLocalUser(newUser: User) {
        getLocalUserUseCase(
            params = Unit,
            onSuccess = { userLocal ->
                newUser.token = userLocal.token
                newUser.refreshToken = userLocal.refreshToken
                updateUserLocal(newUser)
            },
            onError = {
                _responseUpdateEmergencyContact.postError(it)
            }
        )
    }

    private fun updateUserLocal(user: User) {
        updateUserUseCase(
            params = UpdateUserUseCase.Params(
                newUser = user
            ),
            onSuccess = {
                _responseUpdateEmergencyContact.postSuccess(true)
            },
            onError = {
                _responseUpdateEmergencyContact.postError(it)
            }
        )
    }


}