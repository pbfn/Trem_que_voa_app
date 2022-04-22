package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.usecase.SaveCityForUser
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class CityViewModel(
    private val saveCityForUser: SaveCityForUser
) : ViewModel() {

    private var _responseSaveCity = MutableLiveData<ViewState<Boolean>>()
    var responseSaveCity: LiveData<ViewState<Boolean>> = _responseSaveCity

    private var _showProgressBar = MutableLiveData<Boolean>()
    var showProgressBar: LiveData<Boolean> = _showProgressBar

    fun saveCity(city: String) {
        _showProgressBar.postValue(true)
        _responseSaveCity.postLoading()
        saveCityForUser(
            params = SaveCityForUser.Params(
                city = city
            ),
            onSuccess = {
                _responseSaveCity.postSuccess(it)
                _showProgressBar.postValue(false)
            },
            onError = {
                _responseSaveCity.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }

}