package com.br.ioasys.tremquevoa.data_remote.model.response

import com.google.gson.annotations.SerializedName

data class WellnessResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
)
