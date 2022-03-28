package com.br.ioasys.tremquevoa.data_local.mappers

import com.br.ioasys.tremquevoa.data_local.model.UserDataLocal
import com.br.ioasys.tremquevoa.domain.model.User


fun User.toDao(): UserDataLocal = UserDataLocal(
    id = "1",
    email = this.email,
    token = this.token,
    refreshToken = this.refreshToken
)

fun UserDataLocal.toDomain(): User = User(
    email = this.email,
    token = this.token,
    refreshToken = this.refreshToken
)