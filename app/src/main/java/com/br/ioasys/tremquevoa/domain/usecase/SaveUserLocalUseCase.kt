package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SaveUserLocalUseCase(
    private val userRepository: UserRepository,
    scope: CoroutineScope
) : UseCase<SaveUserLocalUseCase.Params, Unit>(scope = scope) {

    data class Params(
        val user: User
    )

    override fun run(params: Params): Flow<Unit> = when {
        params.user.id.isEmpty() -> {
            throw InvalidEmptyIdException()
        }
        params.user.name.isEmpty() -> {
            throw InvalidEmptyFirstNameException()
        }
        params.user.email.isEmpty() -> {
            throw InvalidEmptyEmailException()
        }
        params.user.token.isEmpty() -> {
            throw InvalidEmptyTokenException()
        }
        params.user.refreshToken.isEmpty() -> {
            throw InvalidEmptyRefreshTokenException()
        }

        else -> {
            flowOf(
                userRepository.saveUser(user = params.user)
            )
        }
    }

}