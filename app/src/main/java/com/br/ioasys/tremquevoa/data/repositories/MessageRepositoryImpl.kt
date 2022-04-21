package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.local.UserLocalDataSource
import com.br.ioasys.tremquevoa.data.datasource.remote.MessageRemoteDataSource
import com.br.ioasys.tremquevoa.domain.exceptions.EmptyToken
import com.br.ioasys.tremquevoa.domain.model.Message
import com.br.ioasys.tremquevoa.domain.repositories.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MessageRepositoryImpl(
    private val messageRemoteDataSource: MessageRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : MessageRepository {

    override fun getDailyMessage(): Flow<Message> = flow {
        userLocalDataSource.getToken().collect { token ->
            if (token.isNotEmpty()) {
                messageRemoteDataSource.getDailyMessage(token = token).collect {
                    emit(it)
                }
            } else {
                emit(throw EmptyToken())
            }
        }
    }
}