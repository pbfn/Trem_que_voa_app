package com.br.ioasys.tremquevoa.data.datasource.local

import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    fun saveToken(token: String)
    fun getToken(): Flow<String>
    fun verifyFirstLogin(): Flow<Boolean>
    fun verifyMaintainLogin(): Flow<Boolean>
    fun setFirstLogin()
    fun setMaintainLogin()
    fun saveDateLogin(date: String): Flow<String>
}