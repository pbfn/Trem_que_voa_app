package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(
    val userRepository: UserRepository,
    val scope: CoroutineScope
) : UseCase<Unit, User>(scope = scope) {
    override fun run(params: Unit): Flow<User> = when {
        else-> {
            userRepository.getUser()
        }
    }
}