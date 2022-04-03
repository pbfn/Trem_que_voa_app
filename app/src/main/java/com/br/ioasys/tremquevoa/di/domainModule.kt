package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.domain.usecase.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val domainModule = module {
    single {
        CoroutineScope(Dispatchers.IO)
    }
    factory { LoginUseCase(get(), get()) }
    factory { SaveUserLocalUseCase(get(), get()) }
    factory { RegisterUserUseCase(get(), get()) }
    factory { RegisterEventUseCase(get(), get(), get()) }
    factory { GetActivitiesUseCase(get(), get()) }
}