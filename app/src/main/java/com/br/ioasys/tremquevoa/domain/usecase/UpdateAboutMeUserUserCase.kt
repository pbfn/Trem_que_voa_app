package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class UpdateAboutMeUserUserCase(
    private val userRepository: UserRepository,
    scope: CoroutineScope
) : UseCase<UpdateAboutMeUserUserCase.Params, User>(scope = scope) {

    data class Params(
        val token: String,
        val aboutMe: String
    )

    override fun run(params: Params): Flow<User> = when {
        else -> {
            userRepository.updateAboutMe(
                token = params.token,
                aboutMe = params.aboutMe
            )
        }

    }
}