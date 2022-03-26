package com.br.ioasys.tremquevoa.data_remote.datasource

import android.util.Log
import com.br.ioasys.tremquevoa.data.datasource.remote.LoginRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.model.LoginRequest
import com.br.ioasys.tremquevoa.data_remote.service.AuthService
import com.br.ioasys.tremquevoa.domain.model.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginDataSourceImpl(
    private val authService: AuthService
) : LoginRemoteDataSource {
    override fun doLogin(email: String, password: String): Flow<User> = flow {
        Log.d("LoginDataSourceImpl", "Pre chamada")
        val response = authService.doLogin(Gson().toJson(LoginRequest(email = email, password = password)))
        //val response = authService.doLogin()
        if (response.isSuccessful) {
            Log.d("LoginDataSourceImpl", "Login ok")
        } else {
            Log.d("LoginDataSourceImpl", "Login erro")
        }
    }
}