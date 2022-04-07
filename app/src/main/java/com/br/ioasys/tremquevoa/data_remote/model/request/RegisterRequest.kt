package com.br.ioasys.tremquevoa.data_remote.model.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("passwordConfirmation")
    val passwordConfirmation: String,
)
