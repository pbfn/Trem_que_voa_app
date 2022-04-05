package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class UpdateEmergencyContactsUserUseCase(
    private val userRepository: UserRepository,
    scope: CoroutineScope
) : UseCase<UpdateEmergencyContactsUserUseCase.Params, Boolean>(scope = scope) {


    data class Params(
        val userId: String,
        val emergencyName: String,
        val emergencyPhone: String?
    )

    override fun run(params: Params): Flow<Boolean> {
        TODO("Not yet implemented")
    }

}