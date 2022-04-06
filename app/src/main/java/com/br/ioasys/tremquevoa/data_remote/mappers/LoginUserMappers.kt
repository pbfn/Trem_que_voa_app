package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.LoginResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.RegisterResponse
import com.br.ioasys.tremquevoa.domain.model.User

fun LoginResponse.toDomain() = User(
    id = this.user.id,
    email = this.user.email,
    name = this.user.name,
    token = this.token,
    refreshToken = this.refreshToken,
    phone = this.user.phone,
    emergencyName = this.user.emergencyName,
    emergencyPhone = this.user.emergencyPhone,
    updatedAt = this.user.updatedAt,
    createdAt = this.user.createdAt
)