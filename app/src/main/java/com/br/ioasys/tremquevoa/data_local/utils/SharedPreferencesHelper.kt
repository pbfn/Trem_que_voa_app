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

    fun getBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, true)
}