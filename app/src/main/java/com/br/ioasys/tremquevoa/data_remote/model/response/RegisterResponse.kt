package com.br.ioasys.tremquevoa.data_remote.model.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("emergencyName")
    val emergencyName: String,
    @SerializedName("emergencyPhone")
    val emergencyPhone: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)
