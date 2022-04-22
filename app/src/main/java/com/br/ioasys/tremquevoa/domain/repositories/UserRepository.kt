package com.br.ioasys.tremquevoa.domain.repositories

import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun doLogin(email: String, password: String): Flow<User>


    fun registerUser(
        firstName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<User>

    fun updateEmergencyContactsUser(
        emergencyName: String,
        emergencyPhone: String
    ): Flow<Boolean>


    fun resetPassword(email: String): Flow<Boolean>

    fun updateAboutMe(aboutMe: String): Flow<User>

    fun verifyFirstLogin(): Flow<Boolean>

    fun verifyMaintainLogin(): Flow<Boolean>

    fun setFirstLogin()

    fun setMaintainLogin(maintainLogin: Boolean)

    fun saveDateLogin(date: String): Flow<String>

    fun getUser(): Flow<User>

    fun updateCityForUser(city: String): Flow<Boolean>
}