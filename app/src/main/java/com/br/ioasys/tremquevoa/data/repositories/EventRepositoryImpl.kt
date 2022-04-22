package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.local.UserLocalDataSource
import com.br.ioasys.tremquevoa.data.datasource.remote.EventRemoteDataSource
import com.br.ioasys.tremquevoa.domain.exceptions.EmptyToken
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.repositories.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class EventRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val eventRemoteDataSource: EventRemoteDataSource
) : EventRepository {
    override fun registerEvent(
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
    ): Flow<Unit> = flow {
        userLocalDataSource.getToken().combine(userLocalDataSource.getUserID()) { token, userID ->
            Pair<String, String>(token, userID)
        }.collect { pairCombine ->
            if (pairCombine.first.isNotEmpty() && pairCombine.second.isNotEmpty()) {
                eventRemoteDataSource.registerEvent(
                    token = pairCombine.first,
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
                    userId = pairCombine.second,
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
            } else {
                emit(throw EmptyToken())
            }
        }
    }

    override fun getEvents(): Flow<List<Event>> = flow {
        userLocalDataSource.getToken().collect { token ->
            if (token.isNotEmpty()) {
                eventRemoteDataSource
                    .getEvent(token)
                    .combine(
                        eventRemoteDataSource
                            .getAttendeesEventByStatus(token, "SAVED")
                    ) { listEvent, listAttendees ->
                        listEvent.map { e ->
                            listAttendees.forEach { a ->
                                e.isFavorite = e.id == a.eventId
                            }
                            e
                        }
                    }
                    .collect {
                        emit(it)
                    }
            } else {
                emit(throw EmptyToken())
            }
        }
    }

    override fun registerParticipateEvent(
        status: String,
        eventId: String
    ): Flow<Unit> = flow {
        userLocalDataSource.getToken().collect { token ->
            if (token.isNotEmpty()) {
                eventRemoteDataSource.registerParticipateEvent(
                    token = token,
                    status = status,
                    eventId = eventId
                ).collect {
                    emit(Unit)
                }
            } else {
                emit(throw EmptyToken())
            }
        }
    }
}

//    override fun fetchEventActivities(): Flow<List<Activities>> {
//        return registerEventRemoteDataSource.fetchEventActivities()
//    }




