package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.usecase.UpdateEmergencyContactsUserUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class UpdateUserViewModel(
    private val updateEmergencyContactsUserUseCase: UpdateEmergencyContactsUserUseCase,
) : ViewModel() {

    private var _responseUpdateEmergencyContact = MutableLiveData<ViewState<Boolean>>()
    var responseUpdateEmergencyContact: LiveData<ViewState<Boolean>> =
        _responseUpdateEmergencyContact

    private var _showProgressBar = MutableLiveData<Boolean>()
    var showProgressBar: LiveData<Boolean> = _showProgressBar


    fun updateEmergencyContact(emergencyName: String, emergencyPhone: String) {
        _showProgressBar.postValue(true)
        _responseUpdateEmergencyContact.postLoading()
        updateEmergencyContactsUserUseCase(
            params = UpdateEmergencyContactsUserUseCase.Params(
                emergencyName = emergencyName,
                emergencyPhone = emergencyPhone
            ),
            onSuccess = {
                _responseUpdateEmergencyContact.postSuccess(it)
                _showProgressBar.postValue(false)
            },
            onError = {
                _responseUpdateEmergencyContact.postError(it)
                _showProgressBar.postValue(false)
            }

        )
    }


}