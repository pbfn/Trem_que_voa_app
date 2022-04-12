package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.InterestsResponse
import com.br.ioasys.tremquevoa.domain.model.Interests


fun List<InterestsResponse>.toDomain(): MutableList<Interests> {
    val interests: MutableList<Interests> = mutableListOf()
    for (interest in this) {
        if (interest.active) {
            interests.add(interest.toDomain())
        }
    }
    return interests
}

fun InterestsResponse.toDomain(): Interests = Interests(
    id = this.id,
    title = this.name,
    selected = false,
    urlInactive = this.urlInactive,
    urlActive = this.urlActive
)