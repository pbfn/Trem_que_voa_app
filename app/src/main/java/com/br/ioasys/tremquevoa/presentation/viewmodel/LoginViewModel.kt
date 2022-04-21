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

    fun doLogin(email: String, password: String) {
        _user.postLoading()

        loginUseCase(
            params = LoginUseCase.Params(
                email = email,
                password = password
            ),
            onSuccess = { userReponse ->
                _user.postSuccess(userReponse)
            },
            onError = {
                _user.postError(it)
            }

        )
    }

    fun setMaintainLogin() {
        _maintainLogin.postLoading()

        setMaintainLoginUseCase(
            params = Unit,
            onSuccess = {
                _maintainLogin.postSuccess(true)
            },
            onError = {
                _maintainLogin.postError(it)
            }
        )
    }

}