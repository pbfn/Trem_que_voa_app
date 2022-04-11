package com.br.ioasys.tremquevoa.data.datasource.remote

import com.br.ioasys.tremquevoa.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface RegisterEventRemoteDataSource {

    fun registerEvent(
        token:String,
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
        accessibilities: List<String>,
        street: String,
        number: Int,
        city: String,
        state: String,
        zipCode: String,
        referencePoint: String,
        eventId: String,
    ): Flow<Event>

    //fun fetchEventActivities(): Flow<List<Activities>>
}