package com.br.ioasys.tremquevoa.data_remote.model.response

import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.google.gson.annotations.SerializedName

class EventAccessibilitiesResponse (
    @SerializedName("id")
    val id: String,
    @SerializedName("disabilityId")
    val disabilityId: String,
    @SerializedName("eventId")
    val eventId: String,
    val createdAt: String,
    val updatedAt: String?,
    val deletedAt: String?,
    @SerializedName("acessibilities")
    val acessibilities: DisabilitiesResponse?
)
