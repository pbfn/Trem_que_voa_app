package com.br.ioasys.tremquevoa.data_remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdateEmergencyContactUserRequest(
    @SerializedName("emergencyName")
    val emergencyName: String,
    @SerializedName("emergencyPhone")
    val emergencyPhone: String,
)