package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.event.RegisterEventResponse
import com.br.ioasys.tremquevoa.domain.model.Event

fun RegisterEventResponse.toDomain() = Event(
    id = this.eventResponse.id,
    name = this.eventResponse.name,
    description = this.eventResponse.description,
    isOnline = this.eventResponse.isOnline,
    url = this.eventResponse.url,
    date = this.eventResponse.date,
    isPetFriendly = this.eventResponse.isPetFriendly,
    maxParticipants = this.eventResponse.maxParticipants,
    startTime = this.eventResponse.startTime,
    endTime = this.eventResponse.endTime?:"",
    activityId = this.eventResponse.activityId,
    price = this.eventResponse.price,
    userId = this.eventResponse.userId,
    userIdentity = this.eventResponse.userIdentity,
    accessibilities = this.eventAccessibilities?.acessibilities?: arrayListOf(),
    street = this.address.street,
    number = this.address.number,
    city = this.address.city,
    state = this.address.state,
    zipCode = this.address.zipCode,
    referencePoint = this.address.referencePoint
)

fun List<RegisterEventResponse>.toDomain(): List<Event> {
    return this.map {
        it.toDomain()
    }
}