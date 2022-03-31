package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.RegisterResponse
import com.br.ioasys.tremquevoa.domain.model.User


fun RegisterResponse.toDomain() = User(
    id = this.id,
    email = this.email,
    firstName = this.firstName,
    lastName = this.lastName,
    token = "token",
    refreshToken = "refresh-token"
)