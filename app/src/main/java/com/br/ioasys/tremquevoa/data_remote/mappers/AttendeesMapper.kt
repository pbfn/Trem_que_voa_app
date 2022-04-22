package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.attendees.AttendeesResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.attendees.DataListAttendeesResponse
import com.br.ioasys.tremquevoa.domain.model.Attendees

fun AttendeesResponse.toDomain(): Attendees = Attendees(
    id = this.id,
    eventId = this.eventId,
    status = this.status,
    userId = this.userId,
    updatedAt = this.updatedAt,
    deletedAt = this.deletedAt,
    createdAt = this.createdAt
)

fun DataListAttendeesResponse.toDomain(): List<Attendees> {
    return this.map {
        it.toDomain()
    }
}