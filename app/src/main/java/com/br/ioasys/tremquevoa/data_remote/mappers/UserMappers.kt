package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.UserResponse
import com.br.ioasys.tremquevoa.domain.model.User

fun UserResponse.toDomain() = User(
    id = this.id,
    name = this.name,
    email = this.email,
    token = "",
    refreshToken = "",
    phone = this.phone,
    aboutMe = this.aboutMe,
    emergencyName = this.emergencyName,
    emergencyPhone = this.emergencyPhone,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    isPremium = this.isPremium,
    isAdm = this.isAdmin,
    city = this.city
)