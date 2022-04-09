package com.br.ioasys.tremquevoa.data_remote.datasource

import com.br.ioasys.tremquevoa.data.datasource.remote.RegisterEventRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.model.request.RegisterEventRequest
import com.br.ioasys.tremquevoa.data_remote.service.EventService
import com.br.ioasys.tremquevoa.domain.model.Activities
import com.br.ioasys.tremquevoa.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterEventDataSourceImpl(
    private val eventService: EventService
) : RegisterEventRemoteDataSource {
    override fun registerEvent(
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
        address: String
    ): Flow<Event> = flow {
        val response = eventService.registerEvent(
            token = token,
            RegisterEventRequest(
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
                address = address
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