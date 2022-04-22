package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.usecase.ResetPasswordUserUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class ForgotPasswordViewModel(
    private val resetPasswordUserUseCase: ResetPasswordUserUseCase
) : ViewModel() {

    private val _resetPassword = MutableLiveData<ViewState<Boolean>>()
    val resetPassword: LiveData<ViewState<Boolean>> = _resetPassword

    private var _showProgressBar = MutableLiveData<Boolean>()
    var showProgressBar: LiveData<Boolean> = _showProgressBar

    fun resetPassword(email: String) {
        _showProgressBar.postValue(true)
        _resetPassword.postLoading()

        resetPasswordUserUseCase(
            params = ResetPasswordUserUseCase.Params(
                email = email
            ),
            onSuccess = {
                _resetPassword.postSuccess(it)
                _showProgressBar.postValue(false)
            },
            onError = {
                _resetPassword.postError(it)
                _showProgressBar.postValue(false)
            }
        )

    }

}