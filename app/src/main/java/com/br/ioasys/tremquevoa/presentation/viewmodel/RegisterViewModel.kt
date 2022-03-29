package com.br.ioasys.tremquevoa.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.usecase.RegisterUserUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postSuccess

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private var _user = MutableLiveData<ViewState<User>>()
    var user: LiveData<ViewState<User>> = _user

    fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ) {
        _user.postValue(ViewState.Loading)

        try {
            registerUserUseCase(
                params = RegisterUserUseCase.Params(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password,
                    passwordConfirmation = passwordConfirmation
                ),
                onSuccess = {
                    _user.postSuccess(it)
                },
                onError = {
                    _user.postError(it)
                }
            )
        } catch (err: Exception) {
            Log.d("Testes", "erro", err)
        }

    }

}