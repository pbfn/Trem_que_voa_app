package com.br.ioasys.tremquevoa.data_remote.service

import com.br.ioasys.tremquevoa.data_remote.model.request.UserInterestsRequest
import com.br.ioasys.tremquevoa.data_remote.model.response.InterestsResponse
import retrofit2.Response
import retrofit2.http.*

interface InterestsService {

    @Headers("Content-type: application/json")
    @GET("activities/list")
    suspend fun getAllInterests(
        @Header("Authorization") token: String,
    ): Response<List<InterestsResponse>>

    @Headers("Content-type: application/json")
    @GET("users/interests/list/")
    suspend fun getInterestsByUser(
        @Header("Authorization") token: String,
    ): Response<List<InterestsResponse>>

    @Headers("Content-type: application/json")
    @POST("users/interests")
    suspend fun saveInterestsForUser(
        @Header("Authorization") token: String,
        @Body userInterestsRequest: UserInterestsRequest
    ): Response<Unit>
}