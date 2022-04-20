package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.br.ioasys.tremquevoa.domain.usecase.SaveDateLoginUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class HomeActivityViewModel(
    private val saveDateLoginUseCase: SaveDateLoginUseCase
) {

    private val _dateLogin = MutableLiveData<ViewState<String>>()
    val dateLogin: LiveData<ViewState<String>> = _dateLogin


    fun saveDateLogin(date: String) {
        _dateLogin.postLoading()

        saveDateLoginUseCase(
            SaveDateLoginUseCase.Params(
                date = date
            ),
            onSuccess = {
                _dateLogin.postSuccess(it)
            },
            onError = {
                _dateLogin.postError(it)
            }
        )
    }
}