package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.RegisterEventResponse
import com.br.ioasys.tremquevoa.domain.model.Event

fun RegisterEventResponse.toDomain() = Event(
    id = this.id,
    name = this.name,
    description = this.description,
    isOnline = this.isOnline,
    url = this.url,
    date = this.date,
    isPetFriendly = this.isPetFriendly,
    maxParticipants = this.maxParticipants,
    startTime = this.startTime,
    endTime = this.endTime,
    activityId = this.activityId,
    userId = this.userId,
    userIdentity = this.userIdentity,
    accessibilities = this.accessibilities,
    address = this.address
)