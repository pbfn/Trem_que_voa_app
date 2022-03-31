package com.br.ioasys.tremquevoa.data.datasource.remote

import com.br.ioasys.tremquevoa.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface RegisterEventRemoteDataSource {

    fun registerEvent(
        name: String,
        description: String,
        date: String,
        minimumAge: Int,
        maxParticipants: Int,
        startTime: String,
        endTime: String,
        isAccessible: Boolean
    ): Flow<Event>
}