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
    private val getDisabilitiesUseCase: GetDisabilitiesUseCase,
) : ViewModel() {

    private val TAG = "EventViewModel"

    private var _event = MutableLiveData<ViewState<Unit>>()
    var event: LiveData<ViewState<Unit>> = _event

    private var _activities = MutableLiveData<ViewState<List<Interests>>>()
    val activities: LiveData<ViewState<List<Interests>>> = _activities

    private var _disabilities = MutableLiveData<ViewState<List<Disabilities>>>()
    var disabilities: LiveData<ViewState<List<Disabilities>>> = _disabilities

    private var _showProgressBar = MutableLiveData<Boolean>()
    var showProgressBar: LiveData<Boolean> = _showProgressBar

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
        price: Int,
        userIdentity: String,
        accessibilities: List<String>,
        street: String,
        number: Int,
        city: String,
        state: String,
        zipCode: String,
        referencePoint: String
    ) {
        _event.postValue(ViewState.Loading)
        _showProgressBar.postValue(true)
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
                price = price,
                userIdentity = userIdentity,
                accessibilities = accessibilities,
                street = street,
                number = number,
                city = city,
                state = state,
                zipCode = zipCode,
                referencePoint = referencePoint,
            ),
            onSuccess = { event ->
                Log.d(TAG, event.toString())
                _event.postValue(ViewState.Success(event))
                _showProgressBar.postValue(false)
            },
            onError = {
                _event.postValue(ViewState.Error(throwable = it))
                _showProgressBar.postValue(false)
            }
        )
    }

    fun fetchActivities() {
        _activities.postLoading()
        _showProgressBar.postValue(true)
        getInterestsUseCase(
            params = Unit,
            onSuccess = { listInterestsResponse ->
                Log.d(TAG, listInterestsResponse.toString())
                _activities.postSuccess(listInterestsResponse)
                _showProgressBar.postValue(false)
            },
            onError = {
                _activities.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }

    fun fetchDisabilities() {
        _disabilities.postLoading()
        _showProgressBar.postValue(true)
        getDisabilitiesUseCase(
            params = Unit,
            onSuccess = { listDisabilitiesResponse ->
                Log.d(TAG, listDisabilitiesResponse.toString())
                _disabilities.postSuccess(listDisabilitiesResponse)
                _showProgressBar.postValue(false)
            },
            onError = {
                _disabilities.postError(it)
                _showProgressBar.postValue(false)
            }
        )
    }

}