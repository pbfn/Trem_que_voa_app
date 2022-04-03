package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.presentation.viewmodel.InterestsViewModel
import com.br.ioasys.tremquevoa.presentation.viewmodel.LoginViewModel
import com.br.ioasys.tremquevoa.presentation.viewmodel.RegisterViewModel
import com.br.ioasys.tremquevoa.presentation.viewmodel.RegisterEventViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterEventViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { InterestsViewModel(get()) }
}