package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.remote.RegisterEventRemoteDataSource
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.repositories.RegisterEventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class RegisterEventRepositoryImpl(
    private val registerEventRemoteDataSource: RegisterEventRemoteDataSource
) : RegisterEventRepository {
    override fun registerEvent(
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
    ): Flow<Event> = flow {
        registerEventRemoteDataSource.registerEvent(
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
            emit(event)
        }
    }
}



