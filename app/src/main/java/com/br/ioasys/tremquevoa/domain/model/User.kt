package com.br.ioasys.tremquevoa.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val token: String,
    val refreshToken: String,
    val phone: String?,
    val emergencyName: String?,
    val emergencyPhone: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val maintainLogin: Boolean
)
