package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.data.datasource.remote.InterestsRemoteDataSource
import com.br.ioasys.tremquevoa.data.datasource.remote.UserRemoteDataSource
import com.br.ioasys.tremquevoa.data.datasource.remote.RegisterEventRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.datasource.InterestsRemoteDataSourceImpl
import com.br.ioasys.tremquevoa.data_remote.datasource.UserDataSourceImpl
import com.br.ioasys.tremquevoa.data_remote.datasource.RegisterEventDataSourceImpl
import com.br.ioasys.tremquevoa.data_remote.service.AuthService
import com.br.ioasys.tremquevoa.data_remote.service.EventService
import com.br.ioasys.tremquevoa.data_remote.service.InterestsService
import com.br.ioasys.tremquevoa.data_remote.utils.ApiConstants.BASE_URL
import com.br.ioasys.tremquevoa.data_remote.utils.WebServiceFactory
import org.koin.dsl.module

val dataRemoteModule = module {
    single<UserRemoteDataSource> {
        UserDataSourceImpl(get())
    }

    single<RegisterEventRemoteDataSource> {
        RegisterEventDataSourceImpl(get())
    }


    single<InterestsRemoteDataSource> {
        InterestsRemoteDataSourceImpl(get())
    }

    single<AuthService> {
        WebServiceFactory.createWebService(
            okHttpClient = get(),
            url = BASE_URL
        )
    }

    single<EventService> {
        WebServiceFactory.createWebService(
            okHttpClient = get(),
            url = BASE_URL
        )
    }

    single<InterestsService> {
        WebServiceFactory.createWebService(
            okHttpClient = get(),
            url = BASE_URL
        )
    }

    single { WebServiceFactory.providerOkHttClient() }
}