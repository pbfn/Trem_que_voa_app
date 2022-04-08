package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.DisabilitiesResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.InterestsResponse
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.model.Interests

fun List<DisabilitiesResponse>.toDomain(): MutableList<Disabilities> {
    val disabilities: MutableList<Disabilities> = mutableListOf()
    for (disabilitie in this) {
        if (disabilitie.active) {
            disabilities.add(disabilitie.toDomain())
        }
    }
    return disabilities
}

fun DisabilitiesResponse.toDomain(): Disabilities = Disabilities(
    id = this.id,
    name = this.name,
    description = this.description,
    active = false
)