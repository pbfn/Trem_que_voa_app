package com.br.ioasys.tremquevoa.data_remote.datasource

import com.br.ioasys.tremquevoa.data.datasource.remote.EventRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.model.request.event.AddressRequest
import com.br.ioasys.tremquevoa.data_remote.model.request.event.EventRequest
import com.br.ioasys.tremquevoa.data_remote.model.request.event.RegisterEventRequest
import com.br.ioasys.tremquevoa.data_remote.service.EventService
import com.br.ioasys.tremquevoa.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EventDataSourceImpl(
    private val eventService: EventService
) : EventRemoteDataSource {
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
        val response = eventService.registerEvent(
            token = "Bearer $token",
            registerEventRequest = RegisterEventRequest(
                eventRequest = EventRequest(
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
                    userIdentity = userIdentity,
                    accessibilities = accessibilities,
                ),
                address = AddressRequest(
                    street = street,
                    number = number,
                    city = city,
                    state = state,
                    zipCode = zipCode,
                    referencePoint = referencePoint
                )
            )
        )

        if (response.isSuccessful) {
            response.body()?.let { registerEventResponse ->
                emit(registerEventResponse.let { registerResponse ->
                    registerResponse.eventResponse.toDomain().copy(
                        accessibilities =
                        registerResponse.eventAccessibilities?.acessibilities?.toDomain()
                            ?: arrayListOf(),
                        address = registerResponse.address.toDomain(),
                        activity = registerResponse.interestResponse.toDomain()
                    )

                })
            }
        } else {
            emit(error(response.code()))
        }
    }

    override fun getEvent(token: String): Flow<List<Event>> {
        return flow {
            val response = eventService.getEvent(token)
            if (response.isSuccessful) {
                response.body()?.let { registerEventResponse ->
                    emit(registerEventResponse.map {
                        it.toDomain()
                    })
                }
            } else {
                emit(error(response.code()))
            }
        }
    }
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
