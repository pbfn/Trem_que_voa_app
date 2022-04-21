package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.repositories.DisabilitiesRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


class GetDisabilitiesUseCase(
    private val disabilitiesRepository: DisabilitiesRepository,
    scope: CoroutineScope
) : UseCase<Unit, List<Disabilities>>(scope = scope) {



    override fun run(params: Unit): Flow<List<Disabilities>> {
        return disabilitiesRepository.fetchAllDesabilities()
    }
}