package com.br.ioasys.tremquevoa.domain.repositories

import com.br.ioasys.tremquevoa.domain.model.Activities
import com.br.ioasys.tremquevoa.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface RegisterEventRepository {

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
        userId: String,
        isAccessible: Boolean
    ): Flow<Event>

    fun fetchEventActivities(): Flow<List<Activities>>
}