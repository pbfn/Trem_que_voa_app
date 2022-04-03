package com.br.ioasys.tremquevoa.data_remote.model.request

import com.google.gson.annotations.SerializedName

data class RegisterEventRequest (
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("isOnline")
    val isOnline: Boolean,
    @SerializedName("date")
    val date: String,
    @SerializedName("minimumAge")
    val minimumAge: Int,
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
    @SerializedName("isAccessible")
    val isAccessible: Boolean,
)

