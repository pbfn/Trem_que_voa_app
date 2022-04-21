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


    fun saveCity(city: String) {
        _responseSaveCity.postLoading()
        saveCityForUser(
            params = SaveCityForUser.Params(
                city = city
            ),
            onSuccess = {
                _responseSaveCity.postSuccess(it)
            },
            onError = {
                _responseSaveCity.postError(it)
            }
        )
    }

}