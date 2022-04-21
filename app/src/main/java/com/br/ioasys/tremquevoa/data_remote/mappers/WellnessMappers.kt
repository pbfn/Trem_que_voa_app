package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.WellnessResponse
import com.br.ioasys.tremquevoa.domain.model.Wellness

fun List<WellnessResponse>.toDomain(): MutableList<Wellness> {
    val listWellness: MutableList<Wellness> = mutableListOf()
    for (wellness in this) {
        listWellness.add(wellness.toDomain())
    }
    return listWellness
}

fun WellnessResponse.toDomain(): Wellness = Wellness(
    id = this.id,
    title = this.title,
    description = this.description,
    imageUrl = this.imageUrl
)