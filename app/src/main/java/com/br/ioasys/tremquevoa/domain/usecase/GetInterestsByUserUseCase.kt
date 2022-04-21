package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.repositories.InterestsRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetInterestsByUserUseCase(
    private val interestsRepository: InterestsRepository,
    scope: CoroutineScope
) : UseCase<Unit, List<Interests>>(scope = scope) {

    override fun run(params: Unit): Flow<List<Interests>> {
        return interestsRepository.fetchInterestsByUser()
    }
}