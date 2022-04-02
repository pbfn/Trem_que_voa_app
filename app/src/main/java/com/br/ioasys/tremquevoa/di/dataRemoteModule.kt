package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.data.datasource.remote.InterestsRemoteDataSource
import com.br.ioasys.tremquevoa.data.datasource.remote.LoginRemoteDataSource
import com.br.ioasys.tremquevoa.data.datasource.remote.RegisterRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.datasource.InterestsRemoteDataSourceImpl
import com.br.ioasys.tremquevoa.data_remote.datasource.LoginDataSourceImpl
import com.br.ioasys.tremquevoa.data_remote.datasource.RegisterDataSourceImpl
import com.br.ioasys.tremquevoa.data_remote.service.AuthService
import com.br.ioasys.tremquevoa.data_remote.service.InterestsService
import com.br.ioasys.tremquevoa.data_remote.utils.ApiConstants.BASE_URL
import com.br.ioasys.tremquevoa.data_remote.utils.WebServiceFactory
import org.koin.dsl.module

val dataRemoteModule = module {
    single<LoginRemoteDataSource> {
        LoginDataSourceImpl(get())
    }

    single<RegisterRemoteDataSource> {
        RegisterDataSourceImpl(get())
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

    single<InterestsService> {
        WebServiceFactory.createWebService(
            okHttpClient = get(),
            url = BASE_URL
        )
    }

    single { WebServiceFactory.providerOkHttClient() }
}