package com.br.ioasys.tremquevoa.data_remote.model.response.event

class AddressResponse(
    val id: String,
    val street: String,
    val number: Int,
    val city: String,
    val state: String,
    val zipCode: String,
    val referencePoint: String?,
    val eventId: String,
    val createdAt: String,
    val updatedAt: String?,
    val deletedAt: String?

)