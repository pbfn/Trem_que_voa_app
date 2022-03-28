package com.br.ioasys.tremquevoa.domain.usecase

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

    override fun run(params: Params): Flow<Unit> = flowOf(
        loginRepository.saveUser(user = params.user)
    )

}