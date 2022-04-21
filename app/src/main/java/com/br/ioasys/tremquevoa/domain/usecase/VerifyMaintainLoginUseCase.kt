package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class VerifyMaintainLoginUseCase(
    val scope: CoroutineScope,
    private val userRepository: UserRepository
) : UseCase<Unit, Boolean>(scope = scope) {

    override fun run(params: Unit): Flow<Boolean> = when {
        else -> {
            userRepository.verifyMaintainLogin()
        }
    }
}