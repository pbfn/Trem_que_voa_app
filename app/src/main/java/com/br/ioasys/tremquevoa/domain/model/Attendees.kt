package com.br.ioasys.tremquevoa.domain.model

import java.util.*

data class Attendees(
    val id: String,
    val eventId: String,
    val userId: String,
    val status: String,
    val createdAt: Date,
    val deletedAt: Date?,
    val updatedAt: Date?
)