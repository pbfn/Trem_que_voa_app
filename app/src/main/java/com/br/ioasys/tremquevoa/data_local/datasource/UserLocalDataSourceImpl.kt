package com.br.ioasys.tremquevoa.data_local.datasource

import com.br.ioasys.tremquevoa.data.datasource.local.UserLocalDataSource
import com.br.ioasys.tremquevoa.data_local.database.UserDao
import com.br.ioasys.tremquevoa.data_local.mappers.toDao
import com.br.ioasys.tremquevoa.domain.model.User

class UserLocalDataSourceImpl(
    private val userDao: UserDao
) : UserLocalDataSource {
    override fun saveUser(user: User) {
        userDao.wipeTable()
        return userDao.saveUser(
            user = user.toDao()
        )
    }

    override fun fetchUserLogged(): User? {
        return userDao.getUserLogged()
    }

}