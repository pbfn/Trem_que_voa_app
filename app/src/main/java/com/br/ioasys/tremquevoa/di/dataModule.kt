package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.data.repositories.InterestsRepositoryImpl
import com.br.ioasys.tremquevoa.data.repositories.LoginRepositoryImpl
import com.br.ioasys.tremquevoa.data.repositories.RegisterEventRepositoryImpl
import com.br.ioasys.tremquevoa.data.repositories.RegisterRepositoryImpl
import com.br.ioasys.tremquevoa.domain.repositories.InterestsRepository
import com.br.ioasys.tremquevoa.domain.repositories.LoginRepository
import com.br.ioasys.tremquevoa.domain.repositories.RegisterEventRepository
import com.br.ioasys.tremquevoa.domain.repositories.RegisterRepository
import org.koin.dsl.module

val dataModule = module {
    single<LoginRepository> {
        LoginRepositoryImpl(get(), get())
    }

    single<RegisterEventRepository> {
        RegisterEventRepositoryImpl(get())
    }

    single<RegisterRepository> {
        RegisterRepositoryImpl(get())
    }

    single<InterestsRepository>{
        InterestsRepositoryImpl(get())
    }
}