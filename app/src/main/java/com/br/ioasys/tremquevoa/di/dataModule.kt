package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.data.repositories.InterestsRepositoryImpl
import com.br.ioasys.tremquevoa.data.repositories.UserRepositoryImpl
import com.br.ioasys.tremquevoa.data.repositories.RegisterEventRepositoryImpl
import com.br.ioasys.tremquevoa.domain.repositories.InterestsRepository
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.repositories.RegisterEventRepository
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> {
        UserRepositoryImpl(get(), get())
    }

    single<RegisterEventRepository> {
        RegisterEventRepositoryImpl(get())
    }


    single<InterestsRepository>{
        InterestsRepositoryImpl(get())
    }
}