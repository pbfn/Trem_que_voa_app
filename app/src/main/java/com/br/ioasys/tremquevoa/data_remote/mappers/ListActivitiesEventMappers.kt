package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.ActivitiesResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.ListActivitiesResponse
import com.br.ioasys.tremquevoa.domain.model.Activities

fun ActivitiesResponse.toDomain() = Activities(
    id = this.id,
    name = this.name,
    active = this.active,
    createdAt = this.createdAt,
    deletedAt = this.deletedAt,
    updatedAt = this.updatedAt
)

fun ListActivitiesResponse.toDomain(): List<Activities> {
    return this.map {
        it.toDomain()
    }
}