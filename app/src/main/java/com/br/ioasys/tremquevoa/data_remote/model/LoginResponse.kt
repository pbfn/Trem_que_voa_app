package com.br.ioasys.tremquevoa.data_remote.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("name")
    val name: String,
)
