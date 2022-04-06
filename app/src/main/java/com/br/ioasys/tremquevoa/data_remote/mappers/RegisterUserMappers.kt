package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_local.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.model.response.RegisterResponse
import com.br.ioasys.tremquevoa.domain.model.User


fun RegisterResponse.toDomain() = User(
    id = this.id,
    email = this.email,
    name = this.name,
    token = "token",
    refreshToken = "refresh-token",
    phone = this.phone,
    emergencyName = this.emergencyName,
    emergencyPhone = this.emergencyPhone,
    updatedAt = this.updatedAt,
    createdAt = this.createdAt,
    maintainLogin = false
)