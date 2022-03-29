package com.br.ioasys.tremquevoa.data_remote.service

import com.br.ioasys.tremquevoa.data_remote.model.request.LoginRequest
import com.br.ioasys.tremquevoa.data_remote.model.request.RegisterRequest
import com.br.ioasys.tremquevoa.data_remote.model.response.LoginResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {

    @Headers("Content-type: application/json")
    @POST("users/signin")
    suspend fun doLogin(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @POST("users/signup")
    @Headers("Content-type: application/json")
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>
}