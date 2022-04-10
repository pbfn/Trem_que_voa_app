package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.usecase.GetLocalUserUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class SplashViewModel(
    private val getLocalUserUseCase: GetLocalUserUseCase
):ViewModel() {

    private var _user = MutableLiveData<ViewState<User>>()
    var user: LiveData<ViewState<User>> = _user

    init {
        getUserLocal()
    }

    private fun getUserLocal() {
        _user.postLoading()
        getLocalUserUseCase(
            params = Unit,
            onSuccess = {
                _user.postSuccess(it)
            },
            onError = {
                _user.postError(it)
            }
        )
    }
}