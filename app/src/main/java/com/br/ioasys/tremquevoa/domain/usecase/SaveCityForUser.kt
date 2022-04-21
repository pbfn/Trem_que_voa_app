package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.exceptions.EmptyCity
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SaveCityForUser(
    private val userRepository: UserRepository,
    val scope: CoroutineScope
) : UseCase<SaveCityForUser.Params, Boolean>(scope = scope) {

    data class Params(
        val city: String
    )

    override fun run(params: Params): Flow<Boolean> = when {
        params.city.isEmpty() -> {
            throw (EmptyCity())
        }
        else -> {
            userRepository.updateCityForUser(city = params.city)
        }

    }

}