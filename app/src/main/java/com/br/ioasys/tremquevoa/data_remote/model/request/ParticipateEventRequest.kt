package com.br.ioasys.tremquevoa.data_remote.model.request

import com.google.gson.annotations.SerializedName

data class ParticipateEventRequest (
    @SerializedName("status")
    val status: String,
    @SerializedName("eventId")
    val eventId: String,
)