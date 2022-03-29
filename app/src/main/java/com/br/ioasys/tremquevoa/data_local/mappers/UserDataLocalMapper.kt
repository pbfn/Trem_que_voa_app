package com.br.ioasys.tremquevoa.data_local.mappers

import com.br.ioasys.tremquevoa.data_local.model.UserDataLocal
import com.br.ioasys.tremquevoa.domain.model.User


fun User.toDao(): UserDataLocal = UserDataLocal(
    id = this.id,
    email = this.email,
    token = this.token,
    refreshToken = this.refreshToken,
    firstName = this.firstName,
    lastName = this.lastName
)

fun UserDataLocal.toDomain(): User = User(
    id = this.id,
    email = this.email,
    token = this.token,
    refreshToken = this.refreshToken,
    firstName = this.firstName,
    lastName = this.lastName
)