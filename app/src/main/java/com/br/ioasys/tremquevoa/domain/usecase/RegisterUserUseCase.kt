package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.RegisterRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class RegisterUserUseCase(
    private val registerRepository: RegisterRepository,
    scope: CoroutineScope
) : UseCase<RegisterUserUseCase.Params, User>(scope = scope) {

    data class Params(
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String,
        val passwordConfirmation: String
    )

    override fun run(params: Params): Flow<User> = when {
        params.firstName.isEmpty() -> {
            throw InvalidEmptyFirstNameException()
        }
        params.lastName.isEmpty() -> {
            throw InvalidEmptyLastNameException()
        }
        params.email.isEmpty() -> {
            throw InvalidEmptyEmailException()
        }
        params.password.isEmpty() -> {
            throw InvalidEmptyPasswordException()
        }
        params.password.length < 6 ->{
            throw InvalidMinimunPassword()
        }
        params.passwordConfirmation.isEmpty() -> {
            throw InvalidEmptyPasswordConfirmException()
        }
        params.password != params.passwordConfirmation -> {
            throw InvalidDifferPasswordException()
        }
        else -> {
            registerRepository.registerUser(
                firstName = params.firstName,
                lastName = params.lastName,
                email = params.email,
                password = params.password,
                passwordConfirmation = params.passwordConfirmation
            )
        }
    }
}