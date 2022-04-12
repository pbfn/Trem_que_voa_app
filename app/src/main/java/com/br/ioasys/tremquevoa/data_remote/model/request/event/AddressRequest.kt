package com.br.ioasys.tremquevoa.data_remote.model.request.event

class AddressRequest(
    val street: String,
    val number: Int,
    val city: String,
    val state: String,
    val zipCode: String,
    val referencePoint: String,
    val userId: String,
    val eventId: String
)