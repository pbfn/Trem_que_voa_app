package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.repositories.EventRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetAllEventsUseCase(
    private val eventRepository: EventRepository,
    scope: CoroutineScope
) : UseCase<Unit, List<Event>>(scope = scope) {

    override fun run(params: Unit): Flow<List<Event>> {
        return eventRepository.getEvents()
    }
}