package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.data.datasource.remote.*
import com.br.ioasys.tremquevoa.data_remote.datasource.*
import com.br.ioasys.tremquevoa.data_remote.service.*
import com.br.ioasys.tremquevoa.data_remote.utils.ApiConstants.BASE_URL
import com.br.ioasys.tremquevoa.data_remote.utils.WebServiceFactory
import org.koin.dsl.module

val dataRemoteModule = module {
    single<UserRemoteDataSource> {
        UserRemoteDataSourceImpl(get())
    }

    single<EventRemoteDataSource> {
        EventDataSourceImpl(get())
    }


    single<InterestsRemoteDataSource> {
        InterestsRemoteDataSourceImpl(get())
    }

    single<DisabilitiesRemoteDataSource> {
        DisabilitiesRemoteDataSourceImpl(get())
    }

    single<MessageRemoteDataSource> {
        MessageRemoteDataSourceImpl(get())
    }

    single<WellnessRemoteDataSource> {
        WellnessRemoteDataSourceImpl(get())
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

    single<DisabilitiesService> {
        WebServiceFactory.createWebService(
            okHttpClient = get(),
            url = BASE_URL
        )
    }

    single<MessageService> {
        WebServiceFactory.createWebService(
            okHttpClient = get(),
            url = BASE_URL
        )
    }

    single<WellnessService> {
        WebServiceFactory.createWebService(
            okHttpClient = get(),
            url = BASE_URL
        )
    }

    single { WebServiceFactory.providerOkHttClient() }
}