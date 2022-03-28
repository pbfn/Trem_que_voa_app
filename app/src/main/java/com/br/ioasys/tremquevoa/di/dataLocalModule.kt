package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.data.datasource.local.LoginLocalDataSource
import com.br.ioasys.tremquevoa.data_local.datasource.LoginLocalDataSourceImpl
import org.koin.dsl.module

val dataLocalModule = module {
    single<LoginLocalDataSource> { LoginLocalDataSourceImpl(get()) }
}