package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.exceptions.EmpytNameContatct
import com.br.ioasys.tremquevoa.domain.exceptions.EmpytNumberContatct
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class UpdateEmergencyContactsUserUseCase(
    private val userRepository: UserRepository,
    scope: CoroutineScope
) : UseCase<UpdateEmergencyContactsUserUseCase.Params, Boolean>(scope = scope) {


    data class Params(
        val emergencyName: String,
        val emergencyPhone: String
    )

    override fun run(params: Params): Flow<Boolean> = when {
        params.emergencyName.isEmpty() -> {
            throw EmpytNameContatct()
        }
        params.emergencyPhone.isEmpty() -> {
            throw EmpytNumberContatct()
        }
        else -> {
            userRepository.updateEmergencyContactsUser(
                emergencyPhone = params.emergencyPhone,
                emergencyName = params.emergencyName
            )
        }
    }

}