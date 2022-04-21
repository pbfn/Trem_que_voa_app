package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.EventLists
import com.br.ioasys.tremquevoa.domain.model.Message
import com.br.ioasys.tremquevoa.domain.usecase.GetAllEventsUseCase
import com.br.ioasys.tremquevoa.domain.usecase.GetDailyMessageUseCase
import com.br.ioasys.tremquevoa.domain.usecase.SaveDateLoginUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class HomeViewModel(
    private val getAllEventsUseCase: GetAllEventsUseCase,
    private val saveDateLoginUseCase: SaveDateLoginUseCase,
    private val getDailyMessageUseCase: GetDailyMessageUseCase,
) : ViewModel() {

    private var _events = MutableLiveData<ViewState<EventLists>>()
    var events: LiveData<ViewState<EventLists>> = _events


    private var _date = MutableLiveData<ViewState<String>>()
    var date: LiveData<ViewState<String>> = _date

    private var _dailyMessage = MutableLiveData<ViewState<Message>>()
    var dailyMessage: LiveData<ViewState<Message>> = _dailyMessage

    init {
        getEvent()
    }

    private fun getEvent() {
        _events.postLoading()

        getAllEventsUseCase(
            params = Unit,
            onSuccess = { listEvent ->
                _events.postSuccess(listEvent.let { list ->
                    EventLists(
                        listOnline = list.filter {
                            it.isOnline
                        },
                        listPromoted = list.filter {
                            it.isPromoted
                        },
                        listRecommended = list.filter {
                            it.isOnline.not()
                        }
                    )
                })
            },
            onError = {
                _events.postError(it)
            }
        )
    }

    fun verifyDate(date: String) {
        _date.postLoading()
        saveDateLoginUseCase(
            SaveDateLoginUseCase.Params(
                date = date
            ),
            onSuccess = {
                _date.postSuccess(it)
            },
            onError = {
                _date.postError(it)
            }
        )
    }

    fun getDailyMessage() {
        _dailyMessage.postLoading()
        getDailyMessageUseCase(
            params = Unit,
            onSuccess = {
                _dailyMessage.postSuccess(it)
            },
            onError = {
                _dailyMessage.postError(it)
            }
        )
    }
}