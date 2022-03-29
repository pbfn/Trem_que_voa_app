package com.br.ioasys.tremquevoa.data_remote.datasource

import android.util.Log
import com.br.ioasys.tremquevoa.data.datasource.remote.LoginRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.model.request.LoginRequest
import com.br.ioasys.tremquevoa.data_remote.service.AuthService
import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginDataSourceImpl(
    private val authService: AuthService
) : LoginRemoteDataSource {
    override fun doLogin(email: String, password: String): Flow<User> = flow {
        val response = authService.doLogin(LoginRequest(email = email, password = password))
        if (response.isSuccessful) {
            Log.d("LoginDataSourceImpl", "Login ok")
        } else {
            Log.d("LoginDataSourceImpl", "Login erro")
        }
        //TODO REMOVER USER MOCADO
        emit(
            User(
                id = "23123123123",
                firstName = "",
                lastName = "",
                email = "pbruno1283",
                token = "dkjhskajshkasdha",
                refreshToken = "dkajshdkajhsdkjashdkja",
            )
        )
    }
}