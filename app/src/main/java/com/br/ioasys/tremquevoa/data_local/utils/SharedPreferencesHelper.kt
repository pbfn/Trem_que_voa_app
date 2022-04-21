package com.br.ioasys.tremquevoa.data_local.utils

import android.content.Context
import com.br.ioasys.tremquevoa.data_local.utils.LocalConstants.SHARED_PREFERENCES_NAME

class SharedPreferencesHelper(
    context: Context
) {
    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    fun saveBoolean(key: String, value: Boolean) = sharedPreferences.edit().run {
        putBoolean(key, value)
        apply()
    }

    fun getBooleanStandardReturnTrue(key: String): Boolean = sharedPreferences.getBoolean(key, true)

    fun getBooleanStandardReturnFalse(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    fun saveString(key: String, value: String) = sharedPreferences.edit().run {
        putString(key, value)
        apply()
    }

    fun getString(key: String): String = sharedPreferences.getString(key,"")?:""
}