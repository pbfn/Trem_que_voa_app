package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class ResetPasswordUserUseCase(
    private val userRepository: UserRepository,
    val scope: CoroutineScope
) : UseCase<ResetPasswordUserUseCase.Params, Boolean>(scope = scope) {

    data class Params(
        var email: String
    )

    override fun run(params: Params): Flow<Boolean> = when {
        else -> {
            userRepository.resetPassword(email = params.email)
        }
    }

}