package com.br.ioasys.tremquevoa.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    var token: String,
    var refreshToken: String,
    val phone: String?,
    val city: String?,
    val isPremium: Boolean,
    val isAdm: Boolean,
    val aboutMe: String?,
    val emergencyName: String?,
    val emergencyPhone: String?,
    val createdAt: String?,
    val updatedAt: String?,
)
