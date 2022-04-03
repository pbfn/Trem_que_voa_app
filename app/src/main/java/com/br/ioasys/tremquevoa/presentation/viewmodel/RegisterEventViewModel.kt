package com.br.ioasys.tremquevoa.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.ioasys.tremquevoa.domain.model.Activities
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.usecase.GetActivitiesUseCase
import com.br.ioasys.tremquevoa.domain.usecase.RegisterEventUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterEventViewModel(
    private val registerEventUseCase: RegisterEventUseCase,
    private val getActivitiesUseCase: GetActivitiesUseCase
) : ViewModel() {

    private val TAG = "EventViewModel"

    private var _event = MutableLiveData<ViewState<Event>>()
    var event: LiveData<ViewState<Event>> = _event

    private var _activities = MutableLiveData<ViewState<List<Activities>>>()
    val activities: LiveData<ViewState<List<Activities>>> = _activities

    fun registerEvent(
        name: String,
        description: String,
        isOnline: Boolean,
        date: String,
        minimumAge: Int,
        maxParticipants: Int,
        startTime: String,
        endTime: String,
        activityId: String = "",
        userIdentity: String,
        isAccessible: Boolean
    ) {
        _event.postValue(ViewState.Loading)
        viewModelScope.launch {
            try {
                registerEventUseCase.run(
                    RegisterEventUseCase.Params(
                        name = name,
                        description = description,
                        isOnline = isOnline,
                        date = date,
                        minimumAge = minimumAge,
                        maxParticipants = maxParticipants,
                        startTime = startTime,
                        endTime = endTime,
                        activityId = activityId,
                        userId = "",
                        userIdentity = userIdentity,
                        isAccessible = isAccessible
                    )
                ).collect { event ->
                    Log.d(TAG, event.toString())
                    _event.postValue(ViewState.Success<Event>(event))
                }
            } catch (e: Exception) {
                Log.e(TAG, e.message ?: "")
                e.printStackTrace()
                _event.postValue(ViewState.Error(throwable = e))
            }
        }
    }

    fun fetchActivities() {
        viewModelScope.launch {
            _activities.postLoading()

            getActivitiesUseCase.run(Unit).collect {
                Log.d(TAG, it.toString())
                _activities.postSuccess(it)
            }
        }
    }
}