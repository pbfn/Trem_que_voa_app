package com.br.ioasys.tremquevoa.data_local.datasource

import com.br.ioasys.tremquevoa.data.datasource.local.UserLocalDataSource
import com.br.ioasys.tremquevoa.data_local.database.UserDao
import com.br.ioasys.tremquevoa.data_local.mappers.toDao
import com.br.ioasys.tremquevoa.data_local.mappers.toDomain
import com.br.ioasys.tremquevoa.data_local.utils.LocalConstants.FIRST_LOGIN
import com.br.ioasys.tremquevoa.data_local.utils.SharedPreferencesHelper
import com.br.ioasys.tremquevoa.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserLocalDataSourceImpl(
    private val userDao: UserDao,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : UserLocalDataSource {
    override fun saveUser(user: User) {
        userDao.wipeTable()
        return userDao.saveUser(
            user = user.toDao()
        )
    }

    override fun fetchUserLogged(): User {
        return userDao.getUserLogged().toDomain()
    }

    override fun updateUser(user: User) {
        return userDao.updateUser(user.toDao())
    }

    override fun verifyFirstLogin(): Flow<Boolean> = flow {
        emit(sharedPreferencesHelper.getBoolean(FIRST_LOGIN))
    }

    override fun setFirstLogin() {
        sharedPreferencesHelper.saveBoolean(
            key = FIRST_LOGIN,
            value = false
        )
    }

}