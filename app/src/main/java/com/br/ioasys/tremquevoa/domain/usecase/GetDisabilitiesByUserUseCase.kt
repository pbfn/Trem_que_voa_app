package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.repositories.DisabilitiesRepository
import com.br.ioasys.tremquevoa.domain.repositories.InterestsRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetDisabilitiesByUserUseCase(
    private val disabilitiesRepository: DisabilitiesRepository,
    scope: CoroutineScope
) : UseCase<GetDisabilitiesByUserUseCase.Params, List<Disabilities>>(scope = scope) {

    data class Params(
        val token: String
    )

    override fun run(params: Params): Flow<List<Disabilities>> {
        return disabilitiesRepository.fetchDesabilitiesByUser(params.token)
    }
}