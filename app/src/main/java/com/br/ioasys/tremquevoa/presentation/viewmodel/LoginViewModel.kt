package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.usecase.LoginUseCase
import com.br.ioasys.tremquevoa.domain.usecase.SetMaintainLoginUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val setMaintainLoginUseCase: SetMaintainLoginUseCase
) : ViewModel() {

    private var _user = MutableLiveData<ViewState<User>>()
    var user: LiveData<ViewState<User>> = _user

    private var _maintainLogin = MutableLiveData<ViewState<Boolean>>()
    var maintainLogin: LiveData<ViewState<Boolean>> = _maintainLogin

    private var _showProgressBar = MutableLiveData<Boolean>()
    var showProgressBar: LiveData<Boolean> = _showProgressBar

    fun doLogin(email: String, password: String) {
        _user.postLoading()
        _showProgressBar.postValue(true)

        loginUseCase(
            params = LoginUseCase.Params(
                email = email,
                password = password
            ),
            onSuccess = { userReponse ->
                _user.postSuccess(userReponse)
                _showProgressBar.postValue(false)
            },
            onError = {
                _user.postError(it)
                _showProgressBar.postValue(false)
            }

        )
    }

    fun setMaintainLogin(maintain: Boolean) {
        _maintainLogin.postLoading()
        _showProgressBar.postValue(true)
        setMaintainLoginUseCase(
            params = SetMaintainLoginUseCase.Params(
                maintainLogin = maintain
            ),
            onSuccess = {
                _maintainLogin.postSuccess(true)
                _showProgressBar.postValue(false)
            },
            onError = {
                _maintainLogin.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }

}