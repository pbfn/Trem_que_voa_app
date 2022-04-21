package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.Wellness
import com.br.ioasys.tremquevoa.domain.usecase.GetWellnessListUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class WellnessViewModel(
    private val getWellnessListUseCase: GetWellnessListUseCase
) : ViewModel() {

    private var _wellness = MutableLiveData<ViewState<List<Wellness>>>()
    var wellness: LiveData<ViewState<List<Wellness>>> = _wellness

    init {
        getListWellness()
    }

    private fun getListWellness() {
        _wellness.postLoading()
        getWellnessListUseCase(
            params = Unit,
            onSuccess = {
                _wellness.postSuccess(it)
            },
            onError = {
                _wellness.postError(it)
            }
        )
    }
}