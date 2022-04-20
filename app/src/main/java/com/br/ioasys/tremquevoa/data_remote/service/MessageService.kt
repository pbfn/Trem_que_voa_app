package com.br.ioasys.tremquevoa.data_remote.service

import com.br.ioasys.tremquevoa.data_remote.model.response.DailyMessageReponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface MessageService {

    @Headers("Content-type: application/json")
    @GET("messages/daily")
    suspend fun getDailyMessage(
        @Header("Authorization") token: String,
    ):Response<DailyMessageReponse>

}