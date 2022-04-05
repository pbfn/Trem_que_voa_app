package com.br.ioasys.tremquevoa.data_remote.datasource

import android.util.Log
import com.br.ioasys.tremquevoa.data.datasource.remote.RegisterRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.model.request.RegisterRequest
import com.br.ioasys.tremquevoa.data_remote.service.AuthService
import com.br.ioasys.tremquevoa.domain.exceptions.InvalidRegisterException
import com.br.ioasys.tremquevoa.domain.exceptions.UserException
import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterDataSourceImpl(
    private val authService: AuthService
) : RegisterRemoteDataSource {
    override fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<User> = flow {
        val response = authService.registerUser(
            RegisterRequest(
                firstName = firstName,
                lastName = lastName,
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
}