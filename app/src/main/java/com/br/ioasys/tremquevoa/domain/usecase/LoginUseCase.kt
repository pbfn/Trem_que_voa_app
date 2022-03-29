package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.exceptions.InvalidEmptyEmailException
import com.br.ioasys.tremquevoa.domain.exceptions.InvalidEmptyPasswordException
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.LoginRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val loginRepository: LoginRepository,
    scope: CoroutineScope
) : UseCase<LoginUseCase.Params, User>(scope = scope) {

    data class Params(
        val email: String,
        val password: String
    )

    override fun run(params: Params): Flow<User> = when {
        params.email.isEmpty() -> {
            throw InvalidEmptyEmailException()
        }
        params.password.isEmpty() -> {
            throw InvalidEmptyPasswordException()
        }
        else -> {
            loginRepository.doLogin(email = params.email, password = params.password)
        }
    }
}