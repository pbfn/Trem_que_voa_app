package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.DisabilitiesResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.EventAccessibilitiesResponse
import com.br.ioasys.tremquevoa.domain.model.Disabilities

fun List<DisabilitiesResponse>.toDomain(): MutableList<Disabilities> {
    val disabilities: MutableList<Disabilities> = mutableListOf()
    for (disabilitie in this) {
        disabilities.add(disabilitie.toDomain())
    }
    return disabilities
}

fun DisabilitiesResponse.toDomain(): Disabilities = Disabilities(
    id = this.id,
    name = this.name,
    description = this.description,
    active = false
)

fun EventAccessibilitiesResponse.toDomain(): Disabilities = Disabilities(
        id = this.id,
        name = this.acessibilities?.name?:"",
        description = this.acessibilities?.description?:"",
        active = false
)

@JvmName("toDomainEventAccessibilitiesResponse")
fun List<EventAccessibilitiesResponse>.toDomain(): List<Disabilities> {
    return this.map {
        it.toDomain()
    }
}