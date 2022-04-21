package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.usecase.SetFirstLoginUseCase
import com.br.ioasys.tremquevoa.domain.usecase.VerifyFirstLoginUseCase
import com.br.ioasys.tremquevoa.domain.usecase.VerifyMaintainLoginUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class SplashViewModel(
    private val verifyFirstLoginUseCase: VerifyFirstLoginUseCase,
    private val setFirtsLoginUseCase: SetFirstLoginUseCase,
    private val verifyMaintainLoginUseCase: VerifyMaintainLoginUseCase
) : ViewModel() {

    private var _maintainLogin = MutableLiveData<ViewState<Boolean>>()
    var maintainLogin: LiveData<ViewState<Boolean>> = _maintainLogin

    private val _firstLogin = MutableLiveData<ViewState<Boolean>>()
    var firstLogin: LiveData<ViewState<Boolean>> = _firstLogin

    private val _setFirstLogin = MutableLiveData<ViewState<Boolean>>()
    var setFirstLogin: LiveData<ViewState<Boolean>> = _setFirstLogin

    init {
        verifyFirstLogin()
    }

    fun verifyMaintainLogin() {
        _maintainLogin.postLoading()
        verifyMaintainLoginUseCase(
            params = Unit,
            onSuccess = {
                _maintainLogin.postSuccess(it)
            },
            onError = {
                _maintainLogin.postError(it)
            }
        )
    }

    private fun verifyFirstLogin() {
        _firstLogin.postLoading()

        verifyFirstLoginUseCase(
            params = Unit,
            onSuccess = {
                _firstLogin.postSuccess(it)
            },
            onError = {
                _firstLogin.postError(it)
            }
        )
    }

    fun setFirstLogin() {
        _setFirstLogin.postLoading()

        setFirtsLoginUseCase(
            params = Unit,
            onSuccess = {
                _setFirstLogin.postSuccess(true)
            },
            onError = {
                _setFirstLogin.postError(it)
            }
        )
    }
}