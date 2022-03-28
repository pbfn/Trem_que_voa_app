package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.usecase.LoginUseCase
import com.br.ioasys.tremquevoa.domain.usecase.SaveUserLocalUseCase
import com.br.ioasys.tremquevoa.util.ViewState

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val saveUserLocalUseCase: SaveUserLocalUseCase
) : ViewModel() {

    private var _user = MutableLiveData<ViewState<User>>()
    var user: LiveData<ViewState<User>> = _user

    init {
        doLogin("squad8.test@gmail.com", "12345678")
    }

    fun doLogin(email: String, password: String) {
        _user.postValue(ViewState.Loading)

        loginUseCase(
            params = LoginUseCase.Params(
                email = email,
                password = password
            ),
            onSuccess = { userReponse ->
                _user.postValue(ViewState.Success(userReponse))
                saveUser(userReponse)
            },
            onError = {
                _user.postValue(ViewState.Error(it))
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