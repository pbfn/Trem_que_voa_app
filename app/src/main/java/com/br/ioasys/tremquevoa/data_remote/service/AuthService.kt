package com.br.ioasys.tremquevoa.data_remote.service

import com.br.ioasys.tremquevoa.data_remote.model.request.LoginRequest
import com.br.ioasys.tremquevoa.data_remote.model.request.RegisterRequest
import com.br.ioasys.tremquevoa.data_remote.model.request.UpdateEmergencyContactUserRequest
import com.br.ioasys.tremquevoa.data_remote.model.response.LoginResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.*

interface AuthService {

    @Headers("Content-type: application/json")
    @POST("users/signin")
    suspend fun doLogin(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @Headers("Content-type: application/json")
    @POST("users/signup")
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>

    @Headers("Content-type: application/json")
    @PUT("users")
    suspend fun updateEmergencyContactsUser(
        @Header("Authorization") token: String,
        @Body updateEmergencyContactUserRequest: UpdateEmergencyContactUserRequest
    ): Response<RegisterResponse>
}