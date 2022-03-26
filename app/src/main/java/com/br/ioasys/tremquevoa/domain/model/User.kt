package com.br.ioasys.tremquevoa.domain.model

data class User(
    val email: String,
    val token: String,
    val refreshToken: String
)
