package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.repositories.EventRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetAllEventsUseCase(
    private val eventRepository: EventRepository,
    scope: CoroutineScope
) : UseCase<GetAllEventsUseCase.Params, List<Event>>(scope = scope) {

    data class Params(
        var token: String
    )

    override fun run(params: Params): Flow<List<Event>> {
       return eventRepository.getEvents(params.token)
    }
}