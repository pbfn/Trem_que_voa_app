package com.br.ioasys.tremquevoa.data.datasource.remote

import com.br.ioasys.tremquevoa.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRemoteDataSource {

    fun registerEvent(
        token:String,
        id: String,
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
    ): Flow<Event>

    fun getEvent(token: String): Flow<List<Event>>

    //fun fetchEventActivities(): Flow<List<Activities>>
}