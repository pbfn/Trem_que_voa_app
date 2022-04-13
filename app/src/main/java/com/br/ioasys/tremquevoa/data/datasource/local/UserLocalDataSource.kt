package com.br.ioasys.tremquevoa.data.datasource.local

import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    fun saveUser(user: User)
    fun fetchUserLogged(): User
    fun updateUser(user: User)
    fun verifyFirstLogin(): Flow<Boolean>
    fun setFirstLogin()
}