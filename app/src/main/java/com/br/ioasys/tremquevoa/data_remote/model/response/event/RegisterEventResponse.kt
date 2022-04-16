package com.br.ioasys.tremquevoa.data_remote.model.response.event

import com.br.ioasys.tremquevoa.data_remote.model.response.EventAccessibilitiesResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.InterestsResponse
import com.google.gson.annotations.SerializedName

data class RegisterEventResponse (
    @SerializedName("event")
    val eventResponse: EventResponse,
    @SerializedName("activities")
    val interestResponse: InterestsResponse,
    @SerializedName("address")
    val address: AddressResponse,
    @SerializedName("eventAccessibilities")
    val eventAccessibilities: EventAccessibilitiesResponse?,

)

