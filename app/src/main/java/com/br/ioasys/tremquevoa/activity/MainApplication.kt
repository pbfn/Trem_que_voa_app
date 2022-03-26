package com.br.ioasys.tremquevoa.activity

import android.app.Application
import com.br.ioasys.tremquevoa.di.dataModule
import com.br.ioasys.tremquevoa.di.dataRemoteModule
import com.br.ioasys.tremquevoa.di.domainModule
import com.br.ioasys.tremquevoa.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                dataModule,
                dataRemoteModule,
                domainModule,
                presentationModule
            ).androidContext(applicationContext)
        }
    }

}