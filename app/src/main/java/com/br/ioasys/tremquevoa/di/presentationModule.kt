package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.presentation.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterEventViewModel(get(), get(), get()) }
    viewModel { RegisterUserViewModel(get()) }
    viewModel { InterestsViewModel(get(), get()) }
    viewModel { UpdateUserViewModel(get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { PerfilViewModel(get(), get(), get(), get()) }
    viewModel { SplashViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get(), get(), get(), get(), get()) }
    viewModel { DisabilitiesViewModel(get(), get()) }
    viewModel { EventViewModel(get()) }
}