package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.usecase.SetMaintainLoginUseCase
import com.br.ioasys.tremquevoa.domain.usecase.WipeTokenUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class SettingsViewModel(
    private val wipeTokenUseCase: WipeTokenUseCase,
    private val setMaintainLoginUseCase: SetMaintainLoginUseCase
) : ViewModel() {


    private var _cleanToken = MutableLiveData<ViewState<Unit>>()
    var cleanToken: LiveData<ViewState<Unit>> = _cleanToken

    private var _showProgressBar = MutableLiveData<Boolean>()
    var showProgressBar: LiveData<Boolean> = _showProgressBar

    private var _maintainLogin = MutableLiveData<ViewState<Boolean>>()

    fun wipeToken() {
        _cleanToken.postLoading()
        _showProgressBar.postValue(true)
        wipeTokenUseCase(
            params = Unit,
            onSuccess = {
                _cleanToken.postSuccess(Unit)
                _showProgressBar.postValue(false)
            },
            onError = {
                _cleanToken.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }

    fun setMaintainLogin() {
        _maintainLogin.postLoading()
        _showProgressBar.postValue(true)
        setMaintainLoginUseCase(
            params = SetMaintainLoginUseCase.Params(
                maintainLogin = false
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