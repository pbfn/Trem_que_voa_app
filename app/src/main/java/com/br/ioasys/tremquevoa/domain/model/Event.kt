package com.br.ioasys.tremquevoa.domain.model

data class Event (
    val id: String,
    val description: String,
    val name: String,
    val date: String,
    val minimumAge: Int,
    val maxParticipants: Int,
    val startTime: String,
    val endTime: String,
    val isAccessible: Boolean
)