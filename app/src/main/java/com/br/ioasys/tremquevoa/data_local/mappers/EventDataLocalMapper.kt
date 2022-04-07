package com.br.ioasys.tremquevoa.data_local.mappers

import com.br.ioasys.tremquevoa.data_local.model.EventDataLocal
import com.br.ioasys.tremquevoa.domain.model.Event

fun Event.toDao(): EventDataLocal = EventDataLocal(
    id = this.id ,
    name = this.name,
    description = this.description,
    isOnline = this.isOnline,
    date = this.date,
    minimumAge = this.minimumAge,
    maxParticipants = this.maxParticipants,
    startTime = this.startTime,
    endTime = this.endTime,
    activityId = this.activityId,
    userId = this.userId,
    userIdentity = this.userIdentity,
    isAccessible = this.isAccessible
)

fun EventDataLocal.toDomain(): Event = Event(
    id = this.id ,
    name = this.name,
    description = this.description,
    isOnline = this.isOnline,
    date = this.date,
    minimumAge = this.minimumAge,
    maxParticipants = this.maxParticipants,
    startTime = this.startTime,
    endTime = this.endTime,
    activityId = this.activityId,
    userId = this.userId,
    userIdentity = this.userIdentity,
    isAccessible = this.isAccessible
)