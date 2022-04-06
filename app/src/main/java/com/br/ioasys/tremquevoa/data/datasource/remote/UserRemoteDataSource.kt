package com.br.ioasys.tremquevoa.data.datasource.remote

import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {

    fun doLogin(email: String, password: String, maintainLogin: Boolean): Flow<User>

    fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<User>

    fun updateEmergencyContactsUser(
        userId: String,
        emergencyName: String,
        emergencyPhone: String
    ): Flow<Boolean>

}