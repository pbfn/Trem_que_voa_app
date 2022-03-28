package com.br.ioasys.tremquevoa.data_local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.br.ioasys.tremquevoa.data_local.model.UserDataLocal

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: UserDataLocal)

}