package com.br.ioasys.tremquevoa.data_remote.model.response

import com.google.gson.annotations.SerializedName

data class InterestsResponse(
    @SerializedName("token")
    val token: String,
)