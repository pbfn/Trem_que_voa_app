package com.br.ioasys.tremquevoa.data_remote.datasource

import com.br.ioasys.tremquevoa.data.datasource.remote.LoginRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.model.request.LoginRequest
import com.br.ioasys.tremquevoa.data_remote.service.AuthService
import com.br.ioasys.tremquevoa.domain.exceptions.IvalidLoginException
import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginDataSourceImpl(
    private val authService: AuthService
) : LoginRemoteDataSource {
    override fun doLogin(email: String, password: String): Flow<User> = flow {
        val response = authService.doLogin(LoginRequest(email = email, password = password))
        if (response.isSuccessful) {
            response.body()?.let { loginResponse ->
                emit(loginResponse.toDomain())
            }
        } else {
            emit(throw IvalidLoginException())
        }
    }
}