package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.model.Activities
import com.br.ioasys.tremquevoa.domain.repositories.RegisterEventRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetActivitiesUseCase(
    private val registerEventRepository: RegisterEventRepository,
    scope: CoroutineScope
) : UseCase<Unit, List<Activities>>(scope = scope) {

    override fun run(params: Unit): Flow<List<Activities>> {
        return registerEventRepository.fetchEventActivities()
    }
}