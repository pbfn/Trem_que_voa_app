package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.data.datasource.local.UserLocalDataSource
import com.br.ioasys.tremquevoa.data_local.datasource.UserLocalDataSourceImpl
import org.koin.dsl.module

val dataLocalModule = module {
    single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }
}