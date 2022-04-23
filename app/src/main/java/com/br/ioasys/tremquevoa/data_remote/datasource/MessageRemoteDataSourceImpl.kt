package com.br.ioasys.tremquevoa.data_remote.datasource

import com.br.ioasys.tremquevoa.data.datasource.remote.MessageRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.service.MessageService
import com.br.ioasys.tremquevoa.domain.exceptions.RequestException
import com.br.ioasys.tremquevoa.domain.model.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MessageRemoteDataSourceImpl(
    private val messageService: MessageService
) : MessageRemoteDataSource {

    override fun getDailyMessage(token: String): Flow<Message> = flow{
        val response = messageService.getDailyMessage(token ="Bearer $token")
        if (response.isSuccessful){
            response.body()?.let {  messageResponse->
                emit(messageResponse.toDomain())
            }
        }else{
            emit(throw RequestException())
        }
    }

}