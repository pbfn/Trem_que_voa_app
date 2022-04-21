package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.domain.usecase.*
import com.br.ioasys.tremquevoa.domain.usecase.GetInterestsUseCase
import com.br.ioasys.tremquevoa.domain.usecase.LoginUseCase
import com.br.ioasys.tremquevoa.domain.usecase.RegisterUserUseCase
import com.br.ioasys.tremquevoa.domain.usecase.SaveUserLocalUseCase
import com.br.ioasys.tremquevoa.domain.usecase.ResetPasswordUserUseCase
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
    factory { GetInterestsUseCase(get(), get()) }
    factory { GetDisabilitiesUseCase(get(), get()) }
    factory { UpdateEmergencyContactsUserUseCase(get(), get()) }
    factory { SaveInterestsForUserUseCase(get(), get()) }
    factory { GetLocalUserUseCase(get(), get()) }
    factory { UpdateUserUseCase(get(), get()) }
    factory { ResetPasswordUserUseCase(get(), get()) }
    factory { GetInterestsByUserUseCase(get(), get()) }
    factory { UpdateAboutMeUserUserCase(get(), get()) }
    factory { VerifyFirstLoginUseCase(get(), get()) }
    factory { SetFirstLoginUseCase(get(), get()) }
    factory { GetAllEventsUseCase(get(), get()) }
    factory { GetDisabilitiesByUserUseCase(get(), get()) }
    factory { RegisterParticipateEventsUseCase(get(), get(), get()) }
}