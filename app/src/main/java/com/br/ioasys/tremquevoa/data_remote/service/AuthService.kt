package com.br.ioasys.tremquevoa.data_remote.service

import com.br.ioasys.tremquevoa.data_remote.model.LoginRequest
import com.br.ioasys.tremquevoa.data_remote.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {

    @Headers("Content-type: application/json")
    @POST("users/signin")
    suspend fun doLogin(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}