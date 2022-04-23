package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.model.EventLists
import com.br.ioasys.tremquevoa.domain.usecase.GetAllEventsUseCase
import com.br.ioasys.tremquevoa.domain.usecase.RegisterParticipateEventsUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class FavoritiesEventsViewModel(
    private val getAllEventsUseCase: GetAllEventsUseCase
) : ViewModel() {

    private var _events = MutableLiveData<ViewState<List<Event>>>()
    var events: LiveData<ViewState<List<Event>>> = _events


   fun getEventsFavorities() {
        _events.postLoading()

        getAllEventsUseCase(
            params = Unit,
            onSuccess = {
                _events.postSuccess(it.filter {
                    it.isFavorite
                })
            },
            onError = {
                it.printStackTrace()
                _events.postError(it)
            }
        )
    }
}