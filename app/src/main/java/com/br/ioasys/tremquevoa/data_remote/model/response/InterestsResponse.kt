package com.br.ioasys.tremquevoa.data_remote.model.response

import com.google.gson.annotations.SerializedName

data class InterestsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("active")
    val active: Boolean,
)