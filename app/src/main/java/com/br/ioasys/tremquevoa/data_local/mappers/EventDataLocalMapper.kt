package com.br.ioasys.tremquevoa.data_local.mappers

import com.br.ioasys.tremquevoa.data_local.model.EventDataLocal
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.domain.model.Event

fun Event.toDao(): EventDataLocal = EventDataLocal(
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
    street = this.street,
    number = this.number,
    city = this.city,
    state = this.state,
    zipCode = this.zipCode,
    referencePoint = this.referencePoint
)

fun EventDataLocal.toDomain(): Event = Event(
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
    street = this.street,
    number = this.number,
    city = this.city,
    state = this.state,
    zipCode = this.zipCode,
    referencePoint = this.referencePoint
)