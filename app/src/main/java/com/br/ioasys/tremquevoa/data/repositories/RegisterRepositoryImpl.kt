package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.remote.RegisterRemoteDataSource
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class RegisterRepositoryImpl(
    private val registerRemoteDataSource: RegisterRemoteDataSource
) : RegisterRepository {
    override fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<User> = flow {

        registerRemoteDataSource.registerUser(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            passwordConfirmation = passwordConfirmation
        ).collect { user ->
            emit(user)
        }
    }
}