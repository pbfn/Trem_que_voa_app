package com.br.ioasys.tremquevoa.data.datasource.local

import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    fun saveToken(token: String)
    fun saveUserID(userId: String)
    fun getToken(): Flow<String>
    fun getUserID(): Flow<String>
    fun verifyFirstLogin(): Flow<Boolean>
    fun verifyMaintainLogin(): Flow<Boolean>
    fun setFirstLogin()
    fun setMaintainLogin(maintainLogin: Boolean)
    fun saveDateLogin(date: String): Flow<String>
}