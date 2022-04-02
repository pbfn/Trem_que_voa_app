package com.br.ioasys.tremquevoa.data_remote.service

import com.br.ioasys.tremquevoa.data_remote.model.response.InterestsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface InterestsService {

    @Headers("Content-type: application/json")
    @GET("interests")
    suspend fun getAllInterests():Response<InterestsResponse>
}