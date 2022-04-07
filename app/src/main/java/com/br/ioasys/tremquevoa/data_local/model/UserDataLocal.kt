package com.br.ioasys.tremquevoa.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain

@Entity(tableName = "Users")
data class UserDataLocal(
    @PrimaryKey
    val id: String,
    val email: String,
    val token: String,
    val refreshToken: String,
    val name: String,
    val phone: String?,
    val emergencyName: String?,
    val emergencyPhone: String?,
    val updatedAt: String?,
    val createdAt: String?,
    val maintainLogin: Boolean
)
