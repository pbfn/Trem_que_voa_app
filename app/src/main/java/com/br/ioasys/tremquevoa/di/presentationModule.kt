package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.presentation.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterEventViewModel(get(), get()) }
    viewModel { RegisterUserViewModel(get()) }
    viewModel { InterestsViewModel(get(),get()) }
    viewModel { UpdateUserViewModel(get()) }
}