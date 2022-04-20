package com.br.ioasys.tremquevoa.data_remote.model.response

import com.google.gson.annotations.SerializedName

data class DailyMessageReponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("text")
    val text: String,
)
