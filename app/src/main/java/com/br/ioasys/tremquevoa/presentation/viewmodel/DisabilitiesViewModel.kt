package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.usecase.GetDisabilitiesUseCase
import com.br.ioasys.tremquevoa.domain.usecase.SaveDisabilitiesForUserUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class DisabilitiesViewModel(
    private val getDisabilitiesUseCase: GetDisabilitiesUseCase,
    private val saveDisabilitiesForUserUseCase: SaveDisabilitiesForUserUseCase
) : ViewModel() {

    private val _disabilities = MutableLiveData<ViewState<List<Disabilities>>>()
    val disabilities: LiveData<ViewState<List<Disabilities>>> = _disabilities

    private val _responseSaveDisabilities = MutableLiveData<ViewState<Boolean>>()
    val responseSaveDisabilities: LiveData<ViewState<Boolean>> = _responseSaveDisabilities

    private var _showProgressBar = MutableLiveData<Boolean>()
    var showProgressBar: LiveData<Boolean> = _showProgressBar


    fun getDisabilities() {
        _showProgressBar.postValue(true)
        _disabilities.postLoading()
        getDisabilitiesUseCase(
            params = Unit,
            onSuccess = {
                _disabilities.postSuccess(it)
                _showProgressBar.postValue(false)
            },
            onError = {
                _disabilities.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }

    fun saveDisabilitiesByUser(listIdDisabilities: List<String>) {
        _showProgressBar.postValue(true)
        _responseSaveDisabilities.postLoading()
        saveDisabilitiesForUserUseCase(
            SaveDisabilitiesForUserUseCase.Params(
                listIdDisabilities = listIdDisabilities
            ),
            onSuccess = {
                _responseSaveDisabilities.postSuccess(it)
                _showProgressBar.postValue(false)
            },
            onError = {
                _responseSaveDisabilities.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }
}