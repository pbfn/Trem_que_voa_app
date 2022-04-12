package com.br.ioasys.tremquevoa.domain.model

data class Event (
    val id: String,
    val name: String,
    val description: String,
    val isOnline: Boolean,
    val url: String,
    val date: String,
    val isPetFriendly: Boolean,
    val maxParticipants: Int,
    val startTime: String,
    val endTime: String,
    val activityId: String,
    val price: Int,
    val userId: String,
    val userIdentity: String,
    val accessibilities: List<String>,
    val street: String,
    val number: Int,
    val city: String,
    val state: String,
    val zipCode: String,
    val referencePoint: String
)