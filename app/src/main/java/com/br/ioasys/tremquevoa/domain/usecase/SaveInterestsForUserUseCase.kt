package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.repositories.InterestsRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SaveInterestsForUserUseCase(
    private val interestsRepository: InterestsRepository,
    scope: CoroutineScope
) : UseCase<SaveInterestsForUserUseCase.Params, Boolean>(scope) {

    data class Params(
        val listIdInterests: List<String>
    )

    override fun run(params: Params): Flow<Boolean> = when {
        else -> {
            interestsRepository.saveInterestsForUser(
                listIdInterests = params.listIdInterests
            )
        }
    }
}