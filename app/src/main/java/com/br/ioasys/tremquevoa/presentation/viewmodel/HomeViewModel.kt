package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.EventLists
import com.br.ioasys.tremquevoa.domain.model.Message
import com.br.ioasys.tremquevoa.domain.model.*
import com.br.ioasys.tremquevoa.domain.usecase.*
import com.br.ioasys.tremquevoa.util.*

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

    private var _showProgressBar = MutableLiveData<Boolean>()
    var showProgressBar: LiveData<Boolean> = _showProgressBar


    init {
        getUser()
        getEvent()
        getInterest()
        getDisabilities()
        getListWellness()
    }

    private fun getEvent() {
        _events.postLoading()
        _showProgressBar.postValue(true)
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
                _showProgressBar.postValue(false)
            },
            onError = {
                _events.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }

    fun verifyDate(date: String) {
        _date.postLoading()
        _showProgressBar.postValue(true)
        saveDateLoginUseCase(
            SaveDateLoginUseCase.Params(
                date = date
            ),
            onSuccess = {
                _date.postSuccess(it)
                _showProgressBar.postValue(false)
            },
            onError = {
                _date.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }

    fun getDailyMessage() {
        _dailyMessage.postLoading()
        _showProgressBar.postValue(true)
        getDailyMessageUseCase(
            params = Unit,
            onSuccess = {
                _dailyMessage.postSuccess(it)
                _showProgressBar.postValue(false)
            },
            onError = {
                _dailyMessage.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }

    private fun getInterest() {
        _interest.postLoading()
        _showProgressBar.postValue(true)
        getInterestsUseCase(
            params = Unit,
            onSuccess = { listInterest ->
                _interest.postSuccess(listInterest)
                _showProgressBar.postValue(false)
            },
            onError = {
                _interest.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }

    private fun getDisabilities() {
        _disabilities.postLoading()
        _showProgressBar.postValue(true)
        getDisabilitiesUseCase(
            params = Unit,
            onSuccess = { listDisabilities ->
                _disabilities.postSuccess(listDisabilities)
                _showProgressBar.postValue(false)
            },
            onError = {
                _disabilities.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }

    private fun getUser() {
        _user.postLoading()
        _showProgressBar.postValue(true)
        getUserUseCase(
            params = Unit,
            onSuccess = {
                _user.postSuccess(it)
                _showProgressBar.postValue(false)
            },
            onError = {
                _user.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }

    private fun getListWellness() {
        _wellness.postLoading()
        _showProgressBar.postValue(true)
        getWellnessListUseCase(
            params = Unit,
            onSuccess = {
                _wellness.postSuccess(it)
                _showProgressBar.postValue(false)
            },
            onError = {
                _wellness.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }

    fun resetViewState() {
        _date.postNeutral()
        _dailyMessage.postNeutral()
    }
}