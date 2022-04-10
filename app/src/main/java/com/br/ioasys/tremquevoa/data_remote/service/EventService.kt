package com.br.ioasys.tremquevoa.data_remote.service

import com.br.ioasys.tremquevoa.data_remote.model.request.RegisterEventRequest
import com.br.ioasys.tremquevoa.data_remote.model.response.RegisterEventResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface EventService {

    @Headers("Content-type: application/json")
    @POST("events")
    suspend fun registerEvent(
        @Body registerEventRequest: RegisterEventRequest,
        @Header("Authorization") token: String
    ): Response<RegisterEventResponse>

//    @Headers("Content-type: application/json")
//    @GET("activities/list")
//    suspend fun fetchEventActivities(): Response<ListActivitiesResponse>
}