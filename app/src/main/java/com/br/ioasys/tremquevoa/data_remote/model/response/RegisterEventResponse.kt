package com.br.ioasys.tremquevoa.data_remote.model.response

import com.google.gson.annotations.SerializedName

class RegisterEventResponse (
    @SerializedName("id")
    val id: String,
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
    @SerializedName("userIdentity")
    val userIdentity: String,
    @SerializedName("isAccessible")
    val isAccessible: Boolean,
)

