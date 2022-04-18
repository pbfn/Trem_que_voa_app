package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.event.EventResponse
import com.br.ioasys.tremquevoa.domain.model.Event

fun EventResponse.toDomain() = Event(
    id = this.id,
    name = this.name,
    description = this.description,
    isOnline = this.isOnline,
    url = this.url?:"",
    date = this.date,
    isPetFriendly = this.isPetFriendly,
    maxParticipants = this.maxParticipants,
    numParticipants = this.numParticipants,
    startTime = this.startTime,
    endTime = this.endTime ?: "",
    activity = this.activities.toDomain(),
    price = this.price,
    user = this.users.toDomain(),
    userIdentity = this.userIdentity,
    accessibilities = this.eventAccessibilities
        ?.toDomain()?: arrayListOf(),
    address = this.addresses?.toDomain()?.firstOrNull()
)
