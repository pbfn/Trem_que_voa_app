package com.br.ioasys.tremquevoa.data_local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.br.ioasys.tremquevoa.data_local.model.UserDataLocal
import com.br.ioasys.tremquevoa.domain.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: UserDataLocal)

    @Query("SELECT * FROM users")
    fun getUserLogged(): User?

    @Query("DELETE FROM users")
    fun wipeTable()
}