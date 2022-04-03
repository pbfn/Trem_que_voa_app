package com.br.ioasys.tremquevoa.data_remote.model.response

data class ActivitiesResponse(
    val active: Boolean,
    val createdAt: String?,
    val deletedAt: String?,
    val id: String,
    val name: String,
    val updatedAt: String?
)