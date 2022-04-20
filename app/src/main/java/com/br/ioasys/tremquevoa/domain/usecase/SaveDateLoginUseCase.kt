package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SaveDateLoginUseCase(
    private val userRepository: UserRepository,
    val scope: CoroutineScope
) : UseCase<SaveDateLoginUseCase.Params, String>(scope = scope) {

    data class Params(
        val date: String
    )

    override fun run(params: Params): Flow<String> = when {
        else -> {
            userRepository.saveDateLogin(params.date)
        }
    }
}