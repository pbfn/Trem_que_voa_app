package com.br.ioasys.tremquevoa.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Events")
data class EventDataLocal(
    @PrimaryKey
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
    val userId: String,
    val userIdentity: String,
    val accessibilities: String,
    val address: String
)