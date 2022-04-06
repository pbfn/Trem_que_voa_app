package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.local.UserLocalDataSource
import com.br.ioasys.tremquevoa.data.datasource.remote.UserRemoteDataSource
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {

    override fun doLogin(email: String, password: String): Flow<User> = flow {
        userRemoteDataSource.doLogin(email = email, password = password).collect { user ->
            emit(user)
        }
    }

    override fun saveUser(user: User) = userLocalDataSource.saveUser(
        user = user
    )

    override fun fetchUserLogged(): Flow<User?> {
        return userLocalDataSource.fetchUserLogged().let {
            flowOf(it)
        }
    }

    override fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<User> = flow {
        userRemoteDataSource.registerUser(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            passwordConfirmation = passwordConfirmation
        ).collect { user ->
            emit(user)
        }
    }

    override fun updateEmergencyContactsUser(
        userId: String,
        emergencyName: String,
        emergencyPhone: String
    ): Flow<Boolean> = flow {
        userRemoteDataSource.updateEmergencyContactsUser(
            userId = userId,
            emergencyName = emergencyName,
            emergencyPhone = emergencyPhone
        ).collect {
            emit(it)
        }
    }

}