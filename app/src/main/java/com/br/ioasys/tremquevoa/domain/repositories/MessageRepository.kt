package com.br.ioasys.tremquevoa.domain.repositories

import com.br.ioasys.tremquevoa.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    fun getDailyMessage(token: String): Flow<Message>

}