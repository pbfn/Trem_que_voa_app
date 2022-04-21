package com.br.ioasys.tremquevoa.data.datasource.remote

import com.br.ioasys.tremquevoa.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRemoteDataSource {

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
        price: Int,
        userId: String,
        userIdentity: String,
        accessibilities: List<String>,
        street: String,
        number: Int,
        city: String,
        state: String,
        zipCode: String,
        referencePoint: String,
    ): Flow<Unit>

    fun getEvent(token: String): Flow<List<Event>>

    fun registerParticipateEvent(
        token: String,
        status: String,
        eventId: String
    ): Flow<Unit>

    //fun fetchEventActivities(): Flow<List<Activities>>
}