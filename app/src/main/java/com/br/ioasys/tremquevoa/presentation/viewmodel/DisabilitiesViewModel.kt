package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.usecase.GetDisabilitiesUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class DisabilitiesViewModel(
    private val getDisabilitiesUseCase: GetDisabilitiesUseCase
) : ViewModel() {

    private val _disabilities = MutableLiveData<ViewState<List<Disabilities>>>()
    val disabilities: LiveData<ViewState<List<Disabilities>>> = _disabilities

    fun getDisabilities(token: String) {
        _disabilities.postLoading()
        getDisabilitiesUseCase(
            GetDisabilitiesUseCase.Params(
                token = token
            ),
            onSuccess = {
                _disabilities.postSuccess(it)
            },
            onError = {
                _disabilities.postError(it)
            }
        )
    }
}