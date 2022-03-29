package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.LoginResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.RegisterResponse
import com.br.ioasys.tremquevoa.domain.model.User

fun LoginResponse.toDomain() = User(
    id = this.user.id,
    email = this.user.email,
    firstName = this.user.firstName,
    lastName = this.user.lastName,
    token = this.token,
    refreshToken = this.refreshToken
)