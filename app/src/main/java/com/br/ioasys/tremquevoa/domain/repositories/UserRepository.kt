package com.br.ioasys.tremquevoa.domain.repositories

import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun doLogin(email: String, password: String, maintainLogin: Boolean): Flow<User>

    fun saveUser(user: User)

    fun fetchUserLogged(): Flow<User>

    fun registerUser(
        firstName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<User>

    fun updateEmergencyContactsUser(
        emergencyName: String,
        emergencyPhone: String
    ): Flow<User>

    fun updateUser(newUser: User)

    fun resetPassword(email: String): Flow<Boolean>

    fun updateAboutMe(aboutMe: String): Flow<User>

    fun verifyFirstLogin(): Flow<Boolean>

    fun setFirstLogin()

    fun saveDateLogin(date:String):Flow<String>
}