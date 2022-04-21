package com.br.ioasys.tremquevoa.data.datasource.remote

import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {

    fun doLogin(email: String, password: String): Flow<User>

    fun registerUser(
        firstName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<User>

    fun updateEmergencyContactsUser(
        token: String,
        emergencyName: String,
        emergencyPhone: String
    ): Flow<User>

    fun resetPassword(email: String): Flow<Boolean>

    fun updateAboutMeUser(
        token: String,
        aboutMe: String
    ): Flow<User>

    fun getUser(token: String): Flow<User>

    fun updateCityUser(token: String, city: String): Flow<Boolean>
}