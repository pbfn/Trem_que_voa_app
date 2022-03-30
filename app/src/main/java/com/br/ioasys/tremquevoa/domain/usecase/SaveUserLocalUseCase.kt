package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.LoginRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SaveUserLocalUseCase(
    private val loginRepository: LoginRepository,
    scope: CoroutineScope
) : UseCase<SaveUserLocalUseCase.Params, Unit>(scope = scope) {

    data class Params(
        val user: User
    )

    override fun run(params: Params): Flow<Unit> = when {
        params.user.id.isEmpty() -> {
            throw InvalidEmptyIdException()
        }
        params.user.firstName.isEmpty() -> {
            throw InvalidEmptyFirstNameException()
        }
        params.user.lastName.isEmpty() -> {
            throw InvalidEmptyLastNameException()
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
                loginRepository.saveUser(user = params.user)
            )
        }
    }

}