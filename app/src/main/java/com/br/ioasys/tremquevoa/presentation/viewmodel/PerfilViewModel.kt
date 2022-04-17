package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.usecase.*
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class PerfilViewModel(
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val getInterestsByUserUseCase: GetInterestsByUserUseCase,
    private val updateAboutMeUserUserCase: UpdateAboutMeUserUserCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getDisabilitiesByUserUseCase: GetDisabilitiesByUserUseCase
) : ViewModel() {

    private var _interests = MutableLiveData<ViewState<List<Interests>>>()
    var interests: LiveData<ViewState<List<Interests>>> = _interests

    private var _deficiency = MutableLiveData<ViewState<List<Disabilities>>>()
    var deficiency: LiveData<ViewState<List<Disabilities>>> = _deficiency


    private var _userLocal = MutableLiveData<ViewState<User>>()
    var userLocal: LiveData<ViewState<User>> = _userLocal

    private var _user = MutableLiveData<ViewState<User>>()
    var user: LiveData<ViewState<User>> = _user

    private var _updateUser = MutableLiveData<ViewState<Boolean>>()
    var updateUser: LiveData<ViewState<Boolean>> = _updateUser

    init {
        getUserLocal()
    }

    private fun getInterestsUser(token: String) {
        _interests.postLoading()
        getInterestsByUserUseCase(
            GetInterestsByUserUseCase.Params(
                token = token
            ),
            onSuccess = {
                _interests.postSuccess(it)
            },
            onError = {
                _interests.postError(it)
            }
        )
    }

    private fun getDeficiencyUser(token: String) {
        _deficiency.postLoading()
        getDisabilitiesByUserUseCase(
            GetDisabilitiesByUserUseCase.Params(
                token = token
            ),
            onSuccess = {
                _deficiency.postSuccess(it)
            },
            onError = {
                _deficiency.postError(it)
            }
        )

    }

    private fun getUserLocal() {
        _userLocal.postLoading()
        getLocalUserUseCase(
            params = Unit,
            onSuccess = {
                _userLocal.postSuccess(it)
                getInterestsUser(it.token)
                getDeficiencyUser(it.token)
            },
            onError = {
                _userLocal.postError(it)
                _interests.postError(it)
            }
        )
    }

    fun updateAboutMe(aboutMe: String, token: String) {
        updateAboutMeUserUserCase(
            params = UpdateAboutMeUserUserCase.Params(
                token = token,
                aboutMe = aboutMe
            ),
            onSuccess = {
                _user.postSuccess(it)
            },
            onError = {
                _user.postError(it)
            }
        )
    }

    fun updateUserLocal(newUser: User) {
        updateUserUseCase(
            params = UpdateUserUseCase.Params(
                newUser = newUser
            ),
            onSuccess = {
                _updateUser.postSuccess(true)
            },
            onError = {
                _updateUser.postError(it)
            }
        )
    }

}