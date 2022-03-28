package com.br.ioasys.tremquevoa.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserDataLocal(
    @PrimaryKey
    val id: String,
    val email:String,
    val token: String,
    val refreshToken: String,
)
