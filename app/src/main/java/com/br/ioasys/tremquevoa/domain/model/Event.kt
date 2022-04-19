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
    val numParticipants: Int,
    val startTime: String,
    val isPromoted: Boolean,
    val endTime: String,
    val activity: Interests,
    val price: Double,
    val user: User,
    val userIdentity: String,
    val accessibilities: List<Disabilities>?,
    val address: Address?
)
