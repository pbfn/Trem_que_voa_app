package com.br.ioasys.tremquevoa.domain.model

data class User(
    val id:String,
    val firstName:String,
    val lastName:String,
    val email: String,
    val token: String,
    val refreshToken: String
)
