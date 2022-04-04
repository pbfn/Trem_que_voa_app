package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.local.LoginLocalDataSource
import com.br.ioasys.tremquevoa.data.datasource.remote.LoginRemoteDataSource
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val loginLocalDataSource: LoginLocalDataSource
) : LoginRepository {

    override fun doLogin(email: String, password: String): Flow<User> = flow {
        loginRemoteDataSource.doLogin(email = email, password = password).collect { user ->
            emit(user)
        }
    }

    override fun saveUser(user: User) = loginLocalDataSource.saveUser(
        user = user
    )

    override fun fetchUserLogged(): Flow<User?> {
        return loginLocalDataSource.fetchUserLogged().let {
            flowOf(it)
        }
    }

}