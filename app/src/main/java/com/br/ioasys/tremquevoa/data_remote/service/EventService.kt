package com.br.ioasys.tremquevoa.data_remote.service

import com.br.ioasys.tremquevoa.data_remote.model.request.ParticipateEventRequest
import com.br.ioasys.tremquevoa.data_remote.model.request.event.RegisterEventRequest
import com.br.ioasys.tremquevoa.data_remote.model.response.event.RegisterEventResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.event_list.ListEventResponse
import retrofit2.Response
import retrofit2.http.*

interface EventService {

    @Headers("Content-type: application/json")
    @POST("events")
    suspend fun registerEvent(
        @Body registerEventRequest: RegisterEventRequest,
        @Header("Authorization") token: String
    ): Response<RegisterEventResponse>

    @Headers("Content-type: application/json")
    @GET("events/list")
    suspend fun getEvent(
        @Header("Authorization") token: String
    ): Response<ListEventResponse>

    @Headers("Content-type: application/json")
    @POST("events/attendees")
    suspend fun participateEvent(
        @Body participateEventRequest: ParticipateEventRequest,
        @Header("Authorization") token: String
    ): Response<Unit>

//    @Headers("Content-type: application/json")
//    @GET("activities/list")
//    suspend fun fetchEventActivities(): Response<ListActivitiesResponse>
}