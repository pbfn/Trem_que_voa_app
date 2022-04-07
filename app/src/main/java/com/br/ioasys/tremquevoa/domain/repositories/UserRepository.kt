package com.br.ioasys.tremquevoa.domain.repositories

import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun doLogin(email: String, password: String, maintainLogin: Boolean): Flow<User>

    fun saveUser(user: User)

    fun fetchUserLogged(): Flow<User?>

    fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<User>

    fun updateEmergencyContactsUser(
        token: String,
        emergencyName: String,
        emergencyPhone: String
    ): Flow<Boolean>

}