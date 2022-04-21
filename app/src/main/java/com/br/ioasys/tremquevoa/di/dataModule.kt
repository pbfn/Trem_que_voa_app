package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.data.repositories.*
import com.br.ioasys.tremquevoa.domain.repositories.*
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> {
        UserRepositoryImpl(get(), get())
    }

    single<EventRepository> {
        EventRepositoryImpl(get(), get())
    }


    single<InterestsRepository> {
        InterestsRepositoryImpl(get(), get())
    }

    single<DisabilitiesRepository> {
        DisabilitiesRepositoryImpl(get(), get())
    }

    single<MessageRepository> {
        MessageRepositoryImpl(get(), get())
    }
}