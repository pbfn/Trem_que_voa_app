package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_local.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.model.response.RegisterResponse
import com.br.ioasys.tremquevoa.domain.model.User


fun RegisterResponse.toDomain() = User(
    id = this.user.id,
    email = this.user.email,
    name = this.user.name,
    token = this.token,
    refreshToken = "refresh-token",
    phone = "",
    emergencyName = "",
    emergencyPhone = "",
    updatedAt = this.user.updatedAt,
    createdAt = this.user.createdAt,
    maintainLogin = false,
    aboutMe = "",
    city = "",
    isAdm = false,
    isPremium = false
)