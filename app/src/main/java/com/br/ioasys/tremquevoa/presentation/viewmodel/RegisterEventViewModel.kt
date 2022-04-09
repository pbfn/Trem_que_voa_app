package com.br.ioasys.tremquevoa.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.usecase.GetDisabilitiesUseCase
import com.br.ioasys.tremquevoa.domain.usecase.GetInterestsUseCase
import com.br.ioasys.tremquevoa.domain.usecase.RegisterEventUseCase
import com.br.ioasys.tremquevoa.util.ViewState
import com.br.ioasys.tremquevoa.util.postError
import com.br.ioasys.tremquevoa.util.postLoading
import com.br.ioasys.tremquevoa.util.postSuccess

class RegisterEventViewModel(
    private val registerEventUseCase: RegisterEventUseCase,
    private val getInterestsUseCase: GetInterestsUseCase,
    private val getDisabilitiesUseCase: GetDisabilitiesUseCase
) : ViewModel() {

    private val TAG = "EventViewModel"

    private var _event = MutableLiveData<ViewState<Event>>()
    var event: LiveData<ViewState<Event>> = _event

    private var _activities = MutableLiveData<ViewState<List<Interests>>>()
    val activities: LiveData<ViewState<List<Interests>>> = _activities

    private var _disabilities = MutableLiveData<ViewState<List<Disabilities>>>()
    var disabilities: LiveData<ViewState<List<Disabilities>>> = _disabilities

    fun registerEvent(
        name: String,
        description: String,
        isOnline: Boolean,
        url: String,
        date: String,
        isPetFriendly: Boolean,
        maxParticipants: Int,
        startTime: String,
        endTime: String,
        activityId: String,
        userId: String,
        userIdentity: String,
        accessibilities: String,
        address: String
    ) {
        _event.postValue(ViewState.Loading)

                registerEventUseCase(
                    params = RegisterEventUseCase.Params(
                        name = name,
                        description = description,
                        isOnline = isOnline,
                        url = url,
                        date = date,
                        isPetFriendly = isPetFriendly,
                        maxParticipants = maxParticipants,
                        startTime = startTime,
                        endTime = endTime,
                        activityId = activityId,
                        userId = userId,
                        userIdentity = userIdentity,
                        accessibilities = accessibilities,
                        address = address
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
                userID = null
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

    fun fetchDisabilities() {
        _disabilities.postLoading()
        getDisabilitiesUseCase(
            Unit,
            onSuccess = { listDisabilitiesResponse ->
                Log.d(TAG, listDisabilitiesResponse.toString())
                _disabilities.postSuccess(listDisabilitiesResponse)
            },
            onError = {
                _disabilities.postError(it)
            }
        )
    }
}