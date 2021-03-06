package com.br.ioasys.tremquevoa.data_remote.datasource

import com.br.ioasys.tremquevoa.data.datasource.remote.UserRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.model.request.*
import com.br.ioasys.tremquevoa.data_remote.service.AuthService
import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRemoteDataSourceImpl(
    private val authService: AuthService
) : UserRemoteDataSource {

    override fun doLogin(email: String, password: String): Flow<User> =
        flow {
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
                    emit(throw EmailAlreadyUsed())
                }
                else -> {
                    emit(throw InvalidRegisterException())
                }
            }
        }
    }

    override fun updateEmergencyContactsUser(
        token: String,
        emergencyName: String,
        emergencyPhone: String
    ): Flow<User> = flow {
        val response = authService.updateEmergencyContactsUser(
            token = "Bearer $token",
            UpdateEmergencyContactUserRequest(
                emergencyName = emergencyName,
                emergencyPhone = emergencyPhone
            ),
        )
        if (response.isSuccessful) {
            response.body()?.let { userReponse ->
                emit(userReponse.toDomain())
            }
        } else {
            emit(throw RequestException())
        }
    }


    override fun resetPassword(email: String): Flow<Boolean> = flow {
        val response = authService.resetPassword(ResetPasswordUserRequest(email = email))
        if (response.isSuccessful) {
            emit(true)
        } else {
            emit(throw RequestException())
        }
    }

    override fun updateAboutMeUser(token: String, aboutMe: String): Flow<User> = flow {
        val response = authService.updateAboutMeUser(
            token = "Bearer $token",
            UpdateAboutMeUserRequest(aboutMe = aboutMe)
        )
        if (response.isSuccessful) {
            response.body()?.let { registerReponse ->
                emit(registerReponse.toDomain())
            }
        } else {
            emit(throw RequestException())
        }
    }

    override fun getUser(token: String): Flow<User> = flow {
        val response = authService.getUser(
            token = "Bearer $token",
        )
        if (response.isSuccessful) {
            response.body()?.let { userReponse ->
                emit(userReponse.toDomain())
            }
        } else {
            emit(throw RequestException())
        }
    }

    override fun updateCityUser(token: String, city: String): Flow<Boolean> = flow {
        val response = authService.updateCityUser(
            token = "Bearer $token",
            UpdateCityUserRequest(city = city)
        )

        if (response.isSuccessful) {
            emit(true)
        } else {
            emit(throw RequestException())
        }
    }
}