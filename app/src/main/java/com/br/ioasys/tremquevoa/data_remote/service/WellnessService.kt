package com.br.ioasys.tremquevoa.data_remote.service

import com.br.ioasys.tremquevoa.data_remote.model.response.WellnessResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface WellnessService {
    @Headers("Content-type: application/json")
    @GET("wellness/list")

    suspend fun getListWellness(
        @Header("Authorization") token: String,
    ): Response<List<WellnessResponse>>

}