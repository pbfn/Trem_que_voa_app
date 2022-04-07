package com.br.ioasys.tremquevoa.data_remote.model.response

import com.google.gson.annotations.SerializedName

data class ResetPasswordUserResponse(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("message")
    val message: String,
)