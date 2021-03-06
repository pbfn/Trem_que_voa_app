package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.usecase.RegisterParticipateEventsUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class EventViewModel(
    private val registerParticipateEventsUseCase: RegisterParticipateEventsUseCase,
) : ViewModel() {

    private var _eventParticipate = MutableLiveData<ViewState<Unit>>()
    var eventParticipate: LiveData<ViewState<Unit>> = _eventParticipate

    private var _favoriteEvent = MutableLiveData<ViewState<Boolean>>()
    var favoriteEvent: LiveData<ViewState<Boolean>> = _favoriteEvent


    fun registerParticipateEvent(
        eventId: String
    ) {
        _eventParticipate.postLoading()

        registerParticipateEventsUseCase(
            params = RegisterParticipateEventsUseCase.Params(
                status = "CONFIRMED",
                eventId = eventId
            ),
            onSuccess = {
                _eventParticipate.postSuccess(Unit)
            },
            onError = {
                _eventParticipate.postError(Throwable())
            }
        )
    }

    fun favoriteEvent(
        eventId: String
    ) {
        _favoriteEvent.postLoading()

        registerParticipateEventsUseCase(
            params = RegisterParticipateEventsUseCase.Params(
                status = "SAVED",
                eventId = eventId
            ),
            onSuccess = {
                _favoriteEvent.postSuccess(true)
            },
            onError = {
                _favoriteEvent.postError(Throwable())
            }
        )
    }
}