package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.remote.EventRemoteDataSource
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.repositories.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class EventRepositoryImpl(
    private val eventRemoteDataSource: EventRemoteDataSource
) : EventRepository {
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
    ): Flow<Event> = flow {
        eventRemoteDataSource.registerEvent(
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
            price = price,
            userId = userId,
            userIdentity = userIdentity,
            accessibilities = accessibilities,
            street = street,
            number = number,
            city = city,
            state = state,
            zipCode = zipCode,
            referencePoint = referencePoint,
        ).collect { event ->
            emit(event)
        }
    }

    override fun getEvents(token: String): Flow<List<Event>> {
       return eventRemoteDataSource.getEvent(token)
    }
}

//    override fun fetchEventActivities(): Flow<List<Activities>> {
//        return registerEventRemoteDataSource.fetchEventActivities()
//    }




