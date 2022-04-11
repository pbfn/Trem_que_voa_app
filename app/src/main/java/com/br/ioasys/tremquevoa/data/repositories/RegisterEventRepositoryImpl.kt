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
        token: String,
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
        eventId: String
    ): Flow<Event> = flow {
        registerEventRemoteDataSource.registerEvent(
                token = token,
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
                userId = userId,
                userIdentity = userIdentity,
                accessibilities = accessibilities,
                street = street,
                number = number,
                city = city,
                state = state,
                zipCode = zipCode,
                referencePoint = referencePoint,
                eventId = eventId,
        ).collect { event ->
            emit(event)
        }
    }
}

//    override fun fetchEventActivities(): Flow<List<Activities>> {
//        return registerEventRemoteDataSource.fetchEventActivities()
//    }




