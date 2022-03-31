package com.br.ioasys.tremquevoa.data_remote.datasource

import com.br.ioasys.tremquevoa.data.datasource.remote.RegisterEventRemoteDataSource
import com.br.ioasys.tremquevoa.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterEventDataSourceImpl: RegisterEventRemoteDataSource {
    override fun registerEvent(
        name: String,
        description: String,
        date: String,
        minimumAge: Int,
        maxParticipants: Int,
        startTime: String,
        endTime: String,
        isAccessible: Boolean
    ): Flow<Event> = flow {

        //TODO
    }
}