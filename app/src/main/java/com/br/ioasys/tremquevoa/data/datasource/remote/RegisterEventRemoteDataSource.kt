package com.br.ioasys.tremquevoa.data.datasource.remote

import com.br.ioasys.tremquevoa.domain.model.Activities
import com.br.ioasys.tremquevoa.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface RegisterEventRemoteDataSource {

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
        userId: String,
        userIdentity: String,
        isAccessible: Boolean
    ): Flow<Event>

    //fun fetchEventActivities(): Flow<List<Activities>>
}