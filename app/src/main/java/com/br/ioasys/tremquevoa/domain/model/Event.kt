package com.br.ioasys.tremquevoa.domain.model

data class Event (
    val id: String,
    val name: String,
    val description: String,
    val isOnline: Boolean,
    val date: String,
    val minimumAge: Int,
    val maxParticipants: Int,
    val startTime: String,
    val endTime: String,
    val activityId: String,
    val userIdentity: String,
    val isAccessible: Boolean
)