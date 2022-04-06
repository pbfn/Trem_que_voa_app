package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.usecase.LoginUseCase
import com.br.ioasys.tremquevoa.domain.usecase.SaveUserLocalUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val saveUserLocalUseCase: SaveUserLocalUseCase
) : ViewModel() {

    private var _user = MutableLiveData<ViewState<User>>()
    var user: LiveData<ViewState<User>> = _user


    fun doLogin(email: String, password: String, maintainLogin: Boolean) {
        _user.postLoading()

        loginUseCase(
            params = LoginUseCase.Params(
                email = email,
                password = password,
                maintainLogin = maintainLogin
            ),
            onSuccess = { userReponse ->
                _user.postSuccess(userReponse)
                saveUser(userReponse)
            },
            onError = {
                _user.postError(it)
            }

        )
    }

    private fun saveUser(user: User) {
        saveUserLocalUseCase(
            params = SaveUserLocalUseCase.Params(
                user = user
            )
        )
    }

}