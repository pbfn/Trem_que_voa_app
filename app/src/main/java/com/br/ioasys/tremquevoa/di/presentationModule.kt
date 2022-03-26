package com.br.ioasys.tremquevoa.di

import com.br.ioasys.tremquevoa.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LoginViewModel() }
}