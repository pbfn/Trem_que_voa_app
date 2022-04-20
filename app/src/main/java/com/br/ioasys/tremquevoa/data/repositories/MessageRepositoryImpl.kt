package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.remote.MessageRemoteDataSource
import com.br.ioasys.tremquevoa.domain.model.Message
import com.br.ioasys.tremquevoa.domain.repositories.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MessageRepositoryImpl(
    private val messageRemoteDataSource: MessageRemoteDataSource
) : MessageRepository {

    override fun getDailyMessage(token: String): Flow<Message> = flow {
        messageRemoteDataSource.getDailyMessage(token = token).collect {
            emit(it)
        }
    }
}