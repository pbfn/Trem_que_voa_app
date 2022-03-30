package com.br.ioasys.tremquevoa

import com.br.ioasys.tremquevoa.domain.model.User

val DOMAIN_USER_FAKE = User(
    id = "123456",
    email = "pbruno@gmail.com",
    firstName = "Pedro",
    lastName = "Bruno",
    token = "token",
    refreshToken = "refreshToken"
)

val DOMAIN_USER_FAKE_ID = User(
    id = "",
    email = "pbruno@gmail.com",
    firstName = "Pedro",
    lastName = "Bruno",
    token = "token",
    refreshToken = "refreshToken"
)

val DOMAIN_USER_FAKE_EMAIL = User(
    id = "123456",
    email = "",
    firstName = "Pedro",
    lastName = "Bruno",
    token = "token",
    refreshToken = "refreshToken"
)

val DOMAIN_USER_FAKE_FIRSTNAME = User(
    id = "123456",
    email = "pbruno@gmail.com",
    firstName = "",
    lastName = "Bruno",
    token = "token",
    refreshToken = "refreshToken"
)

val DOMAIN_USER_FAKE_LASTNAME = User(
    id = "123456",
    email = "pbruno@gmail.com",
    firstName = "Pedro",
    lastName = "",
    token = "token",
    refreshToken = "refreshToken"
)

val DOMAIN_USER_FAKE_TOKEN = User(
    id = "123456",
    email = "pbruno@gmail.com",
    firstName = "Pedro",
    lastName = "Bruno",
    token = "",
    refreshToken = "refreshToken"
)

val DOMAIN_USER_FAKE_REFRESHTOKEN = User(
    id = "123456",
    email = "pbruno@gmail.com",
    firstName = "Pedro",
    lastName = "Bruno",
    token = "token",
    refreshToken = ""
)