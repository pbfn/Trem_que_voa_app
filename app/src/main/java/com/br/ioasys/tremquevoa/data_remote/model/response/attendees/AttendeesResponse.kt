package com.br.ioasys.tremquevoa.data_remote.model.response.attendees

import java.util.*

data class AttendeesResponse(
    val id: String,
    val status: String,
    val userId: String,
    val eventId: String,
    val createdAt: Date,
    val deletedAt: Date?,
    val updatedAt: Date?
)