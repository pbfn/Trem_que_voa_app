package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.event.AddressResponse
import com.br.ioasys.tremquevoa.domain.model.Address

fun AddressResponse.toDomain(): Address {
    return Address(
        id = this.id,
        street = this.street,
        number = this.number,
        city = this.city,
        state = this.state,
        zipCode = this.zipCode,
        referencePoint = this.referencePoint?:"",
        eventId = this.eventId,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt?:"",
        deletedAt = this.deletedAt?:""
    )
}

fun List<AddressResponse>.toDomain(): List<Address> {
    return this.map {
        it.toDomain()
    }
}