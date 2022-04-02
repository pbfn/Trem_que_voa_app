package com.br.ioasys.tremquevoa.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.repositories.RegisterEventRepository
import com.br.ioasys.tremquevoa.util.ViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterEventViewModel(
    private val registerEventRepository: RegisterEventRepository
): ViewModel() {

    private val TAG = "EventViewModel"

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
        viewModelScope.launch {
            try {
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
                ).collect { event ->
                    Log.d(TAG, event.toString())
                }
            } catch (e: Exception) {
                Log.e(TAG, e.message?:"")
                e.printStackTrace()
            }

        }

    }
}