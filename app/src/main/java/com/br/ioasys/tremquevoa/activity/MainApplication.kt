package com.br.ioasys.tremquevoa.activity

import android.app.Application
import com.br.ioasys.tremquevoa.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                dataLocalModule,
                dataModule,
                dataRemoteModule,
                domainModule,
                presentationModule
            ).androidContext(applicationContext)
        }
    }

}