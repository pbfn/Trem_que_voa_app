package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.repositories.EventRepository
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RegisterParticipateEventsUseCase(
    private val eventRepository: EventRepository,
    private val userRepository: UserRepository,
    scope: CoroutineScope
) : UseCase<RegisterParticipateEventsUseCase.Params, Unit>(scope = scope) {

    data class Params(
        var token: String,
        var status: String,
        var eventId: String
    )

    override fun run(params: Params): Flow<Unit> {

        params.apply {
            var userId = getUserId()
        }

        return eventRepository.registerParticipateEvent(
            params.token,
            params.status,
            params.eventId
        )
    }

    private fun getUserId(): String {
        return userRepository.fetchUserLogged().map { user ->
            user?.id ?: ""
        }.toString()
    }
}