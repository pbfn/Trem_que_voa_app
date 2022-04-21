package com.br.ioasys.tremquevoa.data_local.mappers

import com.br.ioasys.tremquevoa.data_local.model.UserDataLocal
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.domain.model.User


fun User.toDao(): UserDataLocal = UserDataLocal(
    id = this.id,
    email = this.email,
    token = this.token,
    refreshToken = this.refreshToken,
    name = this.name,
    phone = this.phone,
    emergencyName = this.emergencyName,
    emergencyPhone = this.emergencyPhone,
    updatedAt = this.updatedAt,
    createdAt = this.createdAt,
    maintainLogin = this.maintainLogin,
    aboutMe = this.aboutMe
)

fun UserDataLocal.toDomain(): User = User(
    id = this.id,
    email = this.email,
    token = this.token,
    refreshToken = this.refreshToken,
    name = this.name,
    phone = this.phone,
    emergencyName = this.emergencyName,
    emergencyPhone = this.emergencyPhone,
    updatedAt = this.updatedAt,
    createdAt = this.createdAt,
    maintainLogin = this.maintainLogin,
    aboutMe = this.aboutMe,
    city = "",
    isAdm = false,
    isPremium = false
)