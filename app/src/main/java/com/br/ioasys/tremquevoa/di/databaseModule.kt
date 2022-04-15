package com.br.ioasys.tremquevoa.di

import androidx.room.Room
import com.br.ioasys.tremquevoa.data_local.database.Database
import com.br.ioasys.tremquevoa.data_local.utils.LocalConstants.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries()
            .build()
    }

    single { get<Database>().userDao() }
}