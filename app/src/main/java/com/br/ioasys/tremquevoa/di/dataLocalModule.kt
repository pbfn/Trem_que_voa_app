package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.data.datasource.local.UserLocalDataSource
import com.br.ioasys.tremquevoa.data_local.datasource.UserLocalDataSourceImpl
import com.br.ioasys.tremquevoa.data_local.utils.SharedPreferencesHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataLocalModule = module {
    single { SharedPreferencesHelper(androidContext()) }
    single<UserLocalDataSource> { UserLocalDataSourceImpl(get(), get()) }
}