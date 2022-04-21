package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SetMaintainLoginUseCase(
    val scope: CoroutineScope,
    private val userRepository: UserRepository
) : UseCase<Unit, Unit>(scope = scope) {
    override fun run(params: Unit): Flow<Unit> = when {
        else -> {
            flowOf(userRepository.setMaintainLogin())
        }
    }
}