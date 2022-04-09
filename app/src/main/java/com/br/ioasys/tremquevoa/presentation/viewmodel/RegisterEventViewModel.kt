package com.br.ioasys.tremquevoa.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.usecase.GetInterestsUseCase
import com.br.ioasys.tremquevoa.domain.usecase.RegisterEventUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterEventViewModel(
    private val registerEventUseCase: RegisterEventUseCase,
    private val getInterestsUseCase: GetInterestsUseCase,
) : ViewModel() {

    private val TAG = "EventViewModel"

    private var _event = MutableLiveData<ViewState<Event>>()
    var event: LiveData<ViewState<Event>> = _event

    private var _activities = MutableLiveData<ViewState<List<Interests>>>()
    val activities: LiveData<ViewState<List<Interests>>> = _activities

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

                registerEventUseCase(
                    params = RegisterEventUseCase.Params(
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
                    ),
                    onSuccess = { event ->
                        Log.d(TAG, event.toString())
                        _event.postValue(ViewState.Success(event))
                    },
                    onError = {
                        _event.postValue(ViewState.Error(throwable = it))
                    }
                )
    }

    fun fetchActivities() {
        _activities.postLoading()
        getInterestsUseCase(
            params = GetInterestsUseCase.Params(
                token = "null"
            ),
            onSuccess = { listInterestsResponse ->
                Log.d(TAG, listInterestsResponse.toString())
                _activities.postSuccess(listInterestsResponse)
            },
            onError = {
                _activities.postError(it)
            }
        )
    }

}