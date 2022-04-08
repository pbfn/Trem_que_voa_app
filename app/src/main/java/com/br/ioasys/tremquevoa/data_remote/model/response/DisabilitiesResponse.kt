package com.br.ioasys.tremquevoa.data_remote.model.response

import com.google.gson.annotations.SerializedName

data class DisabilitiesResponse (
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("active")
    val active: Boolean,
)
