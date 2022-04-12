package com.br.ioasys.tremquevoa.data_remote.model.request.event

import com.google.gson.annotations.SerializedName

data class RegisterEventRequest (
    val eventRequest: EventRequest,
    @SerializedName("address")
    val address: AddressRequest
)

