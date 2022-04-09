package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.data.datasource.local.UserLocalDataSource
import com.br.ioasys.tremquevoa.data_local.datasource.UserLocalDataSourceImpl
import com.br.ioasys.tremquevoa.data.datasource.local.EventLocalDataSource
import com.br.ioasys.tremquevoa.data_local.datasource.EventLocalDataSourceImpl
import org.koin.dsl.module

val dataLocalModule = module {
    single<EventLocalDataSource> { EventLocalDataSourceImpl(get()) }
    single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }
}