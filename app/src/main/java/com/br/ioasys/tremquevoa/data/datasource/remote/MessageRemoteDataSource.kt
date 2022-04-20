package com.br.ioasys.tremquevoa.data.datasource.remote

import com.br.ioasys.tremquevoa.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRemoteDataSource {
    fun getDailyMessage(token: String): Flow<Message>
}