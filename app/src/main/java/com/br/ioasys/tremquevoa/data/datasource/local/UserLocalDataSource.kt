package com.br.ioasys.tremquevoa.data.datasource.local

import com.br.ioasys.tremquevoa.domain.model.User

interface UserLocalDataSource {
    fun saveUser(user: User)
    fun fetchUserLogged(): User
    fun updateUser(user: User)
}