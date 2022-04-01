package com.br.ioasys.tremquevoa.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.repositories.RegisterEventRepository
import com.br.ioasys.tremquevoa.util.ViewState

class RegisterEventViewModel(
    private val registerEventRepository: RegisterEventRepository
): ViewModel() {

    private var _event = MutableLiveData<ViewState<Event>>()
    var event: LiveData<ViewState<Event>> = _event

    fun registerEvent(
        name: String,
        description: String,
        isOnline: Boolean,
        date: String,
        minimumAge: Int,
        maxParticipants: Int,
        startTime: String,
        endTime: String,
        activityId: String,
        userIdentity: String,
        isAccessible: Boolean
    ) {
        _event.postValue(ViewState.Loading)

        registerEventRepository.registerEvent(
            name = name,
            description = description,
            isOnline = isOnline,
            date = date,
            minimumAge = minimumAge,
            maxParticipants = maxParticipants,
            startTime = startTime,
            endTime = endTime,
            activityId = activityId,
            userIdentity = userIdentity,
            isAccessible = isAccessible
        )
    }
}