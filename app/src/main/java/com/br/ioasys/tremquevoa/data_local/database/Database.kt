package com.br.ioasys.tremquevoa.data_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.br.ioasys.tremquevoa.data_local.model.UserDataLocal

@Database(entities = [UserDataLocal::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao
}