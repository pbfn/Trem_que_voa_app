package com.br.ioasys.tremquevoa.data_remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdateCityUserRequest(
    @SerializedName("city")
    val city: String,
)
