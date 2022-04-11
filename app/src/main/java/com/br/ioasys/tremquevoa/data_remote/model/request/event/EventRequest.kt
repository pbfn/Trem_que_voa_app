package com.br.ioasys.tremquevoa.data_remote.model.request.event

import com.google.gson.annotations.SerializedName

data class EventRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("isOnline")
    val isOnline: Boolean,
    @SerializedName("url")
    val url: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("isPetFriendly")
    val isPetFriendly: Boolean,
    @SerializedName("maxParticipants")
    val maxParticipants: Int,
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("endTime")
    val endTime: String,
    @SerializedName("activityId")
    val activityId: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("userIdentity")
    val userIdentity: String,
    @SerializedName("accessibilities")
    val accessibilities: List<String>
)

