package com.br.ioasys.tremquevoa.data_local.database

import androidx.room.*
import com.br.ioasys.tremquevoa.data_local.model.UserDataLocal
import com.br.ioasys.tremquevoa.domain.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: UserDataLocal)

    @Query("SELECT * FROM users LIMIT 1")
    fun getUserLogged(): UserDataLocal

    @Query("DELETE FROM users")
    fun wipeTable()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user:UserDataLocal)
}