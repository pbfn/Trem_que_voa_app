package com.br.ioasys.tremquevoa.data_remote.datasource

import com.br.ioasys.tremquevoa.data.datasource.remote.UserRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.model.request.LoginRequest
import com.br.ioasys.tremquevoa.data_remote.model.request.RegisterRequest
import com.br.ioasys.tremquevoa.data_remote.model.request.UpdateEmergencyContactUserRequest
import com.br.ioasys.tremquevoa.data_remote.service.AuthService
import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserDataSourceImpl(
    private val authService: AuthService
) : UserRemoteDataSource {

    override fun doLogin(email: String, password: String): Flow<User> = flow {
        val response = authService.doLogin(LoginRequest(email = email, password = password))
        if (response.isSuccessful) {
            response.body()?.let { loginResponse ->
                emit(loginResponse.toDomain())
            }
        } else {
            when (response.code()) {
                400 -> {
                    emit(throw InvalidUserException())
                }
                401 -> {
                    emit(throw InvalidPasswordException())
                }
                404 -> {
                    emit(throw InvalidEmailExecption())
                }
                else -> {
                    emit(throw IvalidLoginException())
                }
            }
        }
    }

    override fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<User> = flow {
        val response = authService.registerUser(
            RegisterRequest(
                name = firstName,
                email = email,
                password = password,
                passwordConfirmation = passwordConfirmation
            )
        )
        if (response.isSuccessful) {
            response.body()?.let { registerReponse ->
                emit(registerReponse.toDomain())
            }
        } else {
            when (response.code()) {
                400 -> {
                    emit(throw InvalidRegisterException())
                }
                409 -> {
                    emit(throw UserException())
                }
                else -> {
                    emit(throw InvalidRegisterException())
                }
            }
        }
    }

    override fun updateEmergencyContactsUser(
        userId: String,
        emergencyName: String,
        emergencyPhone: String
    ): Flow<Boolean> = flow {
        val response = authService.updateEmergencyContactsUser(
            UpdateEmergencyContactUserRequest(
                userId = "pbruno2@gmail.com",
                emergencyName = emergencyName,
                emergencyPhone = emergencyPhone
            )
        )

        if (response.isSuccessful) {
            emit(true)
        } else {
            emit(false)
        }
    }
}