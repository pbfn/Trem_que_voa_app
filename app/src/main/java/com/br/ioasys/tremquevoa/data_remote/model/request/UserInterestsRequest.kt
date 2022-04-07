package com.br.ioasys.tremquevoa.data_remote.model.request

import com.google.gson.annotations.SerializedName

data class UserInterestsRequest(
    @SerializedName("activityIds")
    val activityIds: List<String>
)
