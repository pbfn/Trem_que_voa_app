package com.br.ioasys.tremquevoa.data_remote.datasource

import com.br.ioasys.tremquevoa.data.datasource.remote.RegisterEventRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.model.response.event.AddressResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.event.EventResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.event.RegisterEventResponse
import com.br.ioasys.tremquevoa.data_remote.service.EventService
import com.br.ioasys.tremquevoa.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterEventDataSourceImpl(
    private val eventService: EventService
) : RegisterEventRemoteDataSource {
    override fun registerEvent(
        token: String,
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
    ): Flow<Event> = flow {
        val response = eventService.registerEvent(
            token = "Bearer $token",
            registerEventRequest = RegisterEventResponse(
                EventResponse(
                    id = id,
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
                ),
                address = AddressResponse(
                    street = street,
                    number = number,
                    city = city,
                    state = state,
                    zipCode = zipCode,
                    referencePoint = referencePoint,
                    userId = userId,
                    eventId = id,
                )
            )
        )
        if (response.isSuccessful) {
            response.body()?.let { registerEventResponse ->
                emit(registerEventResponse.toDomain())
            }
        } else {
            emit(error(response.code()))
        }


//    override fun fetchEventActivities(): Flow<List<Activities>> = flow {
//        val response = eventService.fetchEventActivities()
//        if (response.isSuccessful) {
//            response.body()?.let { registerEventResponse ->
//                emit(registerEventResponse.toDomain())
//            }
//        } else {
//            emit(error(response.code()))
//        }
//    }
    }
}