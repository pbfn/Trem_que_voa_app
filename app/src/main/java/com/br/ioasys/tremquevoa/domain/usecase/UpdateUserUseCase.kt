package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UpdateUserUseCase(
    private val userRepository: UserRepository,
    scope: CoroutineScope
) : UseCase<UpdateUserUseCase.Params, Unit>(scope = scope) {


    data class Params(
        val newUser: User
    )

    override fun run(params: Params): Flow<Unit> = when {
        else -> {
            flowOf(
                userRepository.updateUser(params.newUser)
            )
        }
    }


}