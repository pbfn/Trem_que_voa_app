package com.br.ioasys.tremquevoa.data_remote.model.response.event_list

import com.br.ioasys.tremquevoa.data_remote.model.response.AddressResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.EventAccessibilitiesResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.InterestsResponse
import com.br.ioasys.tremquevoa.data_remote.model.response.UserResponse
import com.google.gson.annotations.SerializedName
import java.util.*

data class EventResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("isOnline")
    val isOnline: Boolean,
    @SerializedName("url")
    val url: String?,
    @SerializedName("date")
    val date: Date,
    @SerializedName("isPetFriendly")
    val isPetFriendly: Boolean,
    @SerializedName("isPromoted")
    val isPromoted: Boolean,
    @SerializedName("maxParticipants")
    val maxParticipants: Int,
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("endTime")
    val endTime: String?,
    @SerializedName("activityId")
    val activityId: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("userId")
    val userId: String?,
    @SerializedName("userIdentity")
    val userIdentity: String,
    @SerializedName("accessibilities")
    val accessibilities: List<String>,
    @SerializedName("users")
    val users: UserResponse,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("deletedAt")
    val deletedAt: String?,
    @SerializedName( "updatedAt")
    val updatedAt: String?,
    @SerializedName( "activities")
    val activities: InterestsResponse?,
    @SerializedName( "addresses")
    val addresses: List<AddressResponse> ?= arrayListOf(),
    @SerializedName( "eventAccessibilities")
    val eventAccessibilities: List<EventAccessibilitiesResponse> ?= arrayListOf(),
    @SerializedName( "numParticipants")
    val numParticipants: Int
)

