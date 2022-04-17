package com.br.ioasys.tremquevoa.data_remote.service

import com.br.ioasys.tremquevoa.data_remote.model.response.DisabilitiesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface DisabilitiesService {

    @Headers("Content-type: application/json")
    @GET("disabilities/list")
    suspend fun getAllDisabilities(
        @Header("Authorization") token: String,
    ): Response<List<DisabilitiesResponse>>

    @Headers("Content-type: application/json")
    @GET("users/disabilities/list/")
    suspend fun getDesabilitiesByUser(
        @Header("Authorization") token: String,
    ): Response<List<DisabilitiesResponse>>
}
