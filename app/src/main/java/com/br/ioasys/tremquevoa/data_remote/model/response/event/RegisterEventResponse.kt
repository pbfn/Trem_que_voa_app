package com.br.ioasys.tremquevoa.data_remote.model.response.event

import com.google.gson.annotations.SerializedName

data class RegisterEventResponse (
    val eventResponse: EventResponse,
    @SerializedName("address")
    val address: AddressResponse
)

