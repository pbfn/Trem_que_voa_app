package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.RegisterEventResponse
import com.br.ioasys.tremquevoa.domain.model.Event

fun RegisterEventResponse.toDomain() = Event(
    id = this.id,
    name = this.name,
    description = this.description,
    isOnline = this.isOnline,
    date = this.date,
    minimumAge = this.minimumAge,
    maxParticipants = this.maxParticipants,
    startTime = this.startTime,
    endTime = this.endTime,
    activityId = this.activityId,
    userIdentity = this.userIdentity,
    isAccessible = this.isAccessible,
    userId = this.userId
)