package com.br.ioasys.tremquevoa.domain.repositories

import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun doLogin(email: String, password: String): Flow<User>

    fun saveUser(user: User)

}