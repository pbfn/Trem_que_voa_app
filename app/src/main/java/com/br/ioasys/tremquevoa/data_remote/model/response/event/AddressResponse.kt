package com.br.ioasys.tremquevoa.data_remote.model.response.event

class AddressResponse(
    val street: String,
    val number: Int,
    val city: String,
    val state: String,
    val zipCode: String,
    val referencePoint: String,
    val userId: String,
    val eventId: String
)