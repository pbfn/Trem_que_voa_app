package com.br.ioasys.tremquevoa.data_remote.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("emergencyName")
    val emergencyName: String?,
    @SerializedName("emergencyPhone")
    val emergencyPhone: String?,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("aboutMe")
    val aboutMe: String?,
    @SerializedName("deletedAt")
    val deletedAt: String?,
    @SerializedName("isPremium")
    val isPremium: Boolean,
    @SerializedName("isAdmin")
    val isAdmin: Boolean,
    @SerializedName("password")
    val password: String,
    @SerializedName("city")
    val city: String
)