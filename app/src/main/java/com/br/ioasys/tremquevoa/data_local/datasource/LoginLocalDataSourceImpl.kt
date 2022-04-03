package com.br.ioasys.tremquevoa.data_local.datasource

import com.br.ioasys.tremquevoa.data.datasource.local.LoginLocalDataSource
import com.br.ioasys.tremquevoa.data_local.database.UserDao
import com.br.ioasys.tremquevoa.data_local.mappers.toDao
import com.br.ioasys.tremquevoa.domain.model.User

class LoginLocalDataSourceImpl(
    private val userDao: UserDao
) : LoginLocalDataSource {
    override fun saveUser(user: User) = userDao.saveUser(
        user = user.toDao()
    )

    override fun fetchUserLogged(): User? {
        return userDao.getUserLogged()
    }

}