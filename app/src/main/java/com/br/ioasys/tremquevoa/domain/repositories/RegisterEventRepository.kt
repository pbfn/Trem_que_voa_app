package com.br.ioasys.tremquevoa.domain.repositories

import com.br.ioasys.tremquevoa.domain.model.Activities
import com.br.ioasys.tremquevoa.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface RegisterEventRepository {

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
        accessibilities: String,
        address: String,
        token: String
    ): Flow<Event>

    //fun fetchEventActivities(): Flow<List<Activities>>
}