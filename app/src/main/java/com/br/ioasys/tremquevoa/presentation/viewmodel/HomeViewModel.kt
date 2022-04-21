package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.EventLists
import com.br.ioasys.tremquevoa.domain.model.Message
import com.br.ioasys.tremquevoa.domain.model.*
import com.br.ioasys.tremquevoa.domain.usecase.*
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class HomeViewModel(
    private val getInterestsUseCase: GetInterestsUseCase,
    private val getDisabilitiesUseCase: GetDisabilitiesUseCase,
    private val getAllEventsUseCase: GetAllEventsUseCase,
    private val saveDateLoginUseCase: SaveDateLoginUseCase,
    private val getDailyMessageUseCase: GetDailyMessageUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getWellnessListUseCase: GetWellnessListUseCase
) : ViewModel() {

    private var _events = MutableLiveData<ViewState<EventLists>>()
    var events: LiveData<ViewState<EventLists>> = _events


    private var _date = MutableLiveData<ViewState<String>>()
    var date: LiveData<ViewState<String>> = _date

    private var _disabilities = MutableLiveData<ViewState<List<Disabilities>>>()
    var disabilities: LiveData<ViewState<List<Disabilities>>> = _disabilities

    private var _interest = MutableLiveData<ViewState<List<Interests>>>()
    var interest: LiveData<ViewState<List<Interests>>> = _interest

    private var _user = MutableLiveData<ViewState<User>>()
    var user: LiveData<ViewState<User>> = _user

    private var _dailyMessage = MutableLiveData<ViewState<Message>>()
    var dailyMessage: LiveData<ViewState<Message>> = _dailyMessage

    private var _wellness = MutableLiveData<ViewState<List<Wellness>>>()
    var wellness: LiveData<ViewState<List<Wellness>>> = _wellness

    init {
        getUser()
        getEvent()
        getInterest()
        getDisabilities()
        getListWellness()
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

    private fun getInterest() {
        _interest.postLoading()

        getInterestsUseCase(
            params = Unit,
            onSuccess = { listInterest ->
                _interest.postSuccess(listInterest)
            },
            onError = {
                _interest.postError(it)
            }
        )
    }

    private fun getDisabilities() {
        _disabilities.postLoading()

        getDisabilitiesUseCase(
            params = Unit,
            onSuccess = { listDisabilities ->
                _disabilities.postSuccess(listDisabilities)
            },
            onError = {
                _disabilities.postError(it)
            }
        )
    }

    private fun getUser() {
        _user.postLoading()

        getUserUseCase(
            params = Unit,
            onSuccess = {
                _user.postSuccess(it)
            },
            onError = {
                _user.postError(it)
            }
        )
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