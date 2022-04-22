package com.br.ioasys.tremquevoa.data_local.datasource

import com.br.ioasys.tremquevoa.data.datasource.local.UserLocalDataSource
import com.br.ioasys.tremquevoa.data_local.utils.LocalConstants.FIRST_LOGIN
import com.br.ioasys.tremquevoa.data_local.utils.LocalConstants.LAST_DATE_LOGIN
import com.br.ioasys.tremquevoa.data_local.utils.LocalConstants.MAINTAIN_LOGIN
import com.br.ioasys.tremquevoa.data_local.utils.LocalConstants.TOKEN
import com.br.ioasys.tremquevoa.data_local.utils.LocalConstants.USER_ID
import com.br.ioasys.tremquevoa.data_local.utils.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserLocalDataSourceImpl(
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : UserLocalDataSource {

    override fun saveToken(token: String) {
        sharedPreferencesHelper.saveString(TOKEN, token)
    }

    override fun saveUserID(userId: String) {
        sharedPreferencesHelper.saveString(USER_ID, userId)
    }

    override fun getToken(): Flow<String> = flow {
        emit(sharedPreferencesHelper.getString(TOKEN))
    }

    override fun getUserID(): Flow<String> = flow {
        emit(sharedPreferencesHelper.getString(USER_ID))
    }

    override fun verifyFirstLogin(): Flow<Boolean> = flow {
        emit(sharedPreferencesHelper.getBooleanStandardReturnTrue(FIRST_LOGIN))
    }

    override fun verifyMaintainLogin(): Flow<Boolean> = flow {
        emit(sharedPreferencesHelper.getBooleanStandardReturnFalse(MAINTAIN_LOGIN))
    }

    override fun setFirstLogin() {
        sharedPreferencesHelper.saveBoolean(
            key = FIRST_LOGIN,
            value = false
        )
    }

    override fun setMaintainLogin(maintainLogin: Boolean) {
        sharedPreferencesHelper.saveBoolean(
            key = MAINTAIN_LOGIN,
            value = maintainLogin
        )
    }

    override fun saveDateLogin(date: String): Flow<String> = flow {
        val lastDate = sharedPreferencesHelper.getString(LAST_DATE_LOGIN)
        sharedPreferencesHelper.saveString(LAST_DATE_LOGIN, date)
        emit(lastDate)
    }

}