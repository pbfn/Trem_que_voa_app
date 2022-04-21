package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.repositories.DisabilitiesRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SaveDisabilitiesForUserUseCase(
    private val disabilitiesRepository: DisabilitiesRepository,
    val scope: CoroutineScope
) : UseCase<SaveDisabilitiesForUserUseCase.Params, Boolean>(scope = scope) {

    data class Params(
        val token: String,
        val listIdDisabilities: List<String>
    )

    override fun run(params: Params): Flow<Boolean> = when {
        else -> {
            disabilitiesRepository.saveDesabilitiesByUser(
                token = params.token,
                listIdDisabilities = params.listIdDisabilities
            )
        }
    }
}