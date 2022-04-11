package com.br.ioasys.tremquevoa.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    var token: String,
    var refreshToken: String,
    val phone: String?,
    val aboutMe: String?,
    val emergencyName: String?,
    val emergencyPhone: String?,
    val createdAt: String?,
    val updatedAt: String?,
    var maintainLogin: Boolean
)
