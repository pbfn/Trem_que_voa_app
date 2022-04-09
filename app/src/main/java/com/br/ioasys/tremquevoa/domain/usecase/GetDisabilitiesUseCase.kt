package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.repositories.DisabilitiesRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


class GetDisabilitiesUseCase(
    private val disabilitiesRepository: DisabilitiesRepository,
    scope: CoroutineScope
) : UseCase<GetDisabilitiesUseCase.Params, List<Disabilities>>(scope = scope) {

    data class Params(
        var token: String
    )

    override fun run(params: Params): Flow<List<Disabilities>> {
        return disabilitiesRepository.fetchAllDesabilities(params.token)
    }
}