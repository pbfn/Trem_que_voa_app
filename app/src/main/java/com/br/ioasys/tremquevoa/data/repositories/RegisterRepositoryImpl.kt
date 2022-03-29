package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.RegisterRepository
import kotlinx.coroutines.flow.Flow

class RegisterRepositoryImpl() : RegisterRepository {
    override fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<User> {
        TODO("Not yet implemented")
    }
}