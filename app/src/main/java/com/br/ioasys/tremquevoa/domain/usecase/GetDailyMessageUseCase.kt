package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.model.Message
import com.br.ioasys.tremquevoa.domain.repositories.MessageRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetDailyMessageUseCase(
    private val messageRepository: MessageRepository,
    val scope: CoroutineScope
) : UseCase<GetDailyMessageUseCase.Params, Message>(scope = scope) {
    data class Params(
        val token: String
    )

    override fun run(params: Params): Flow<Message> = when {
        else -> {
            messageRepository.getDailyMessage(token = params.token)
        }
    }
}