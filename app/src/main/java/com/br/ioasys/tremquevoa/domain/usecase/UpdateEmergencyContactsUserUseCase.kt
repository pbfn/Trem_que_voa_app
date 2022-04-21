package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class UpdateEmergencyContactsUserUseCase(
    private val userRepository: UserRepository,
    scope: CoroutineScope
) : UseCase<UpdateEmergencyContactsUserUseCase.Params, User>(scope = scope) {


    data class Params(
        val emergencyName: String,
        val emergencyPhone: String
    )

    override fun run(params: Params): Flow<User> = when {
        else -> {
            userRepository.updateEmergencyContactsUser(
                emergencyPhone = params.emergencyPhone,
                emergencyName = params.emergencyName
            )
        }
    }

}