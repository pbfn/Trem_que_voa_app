package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.model.Wellness
import com.br.ioasys.tremquevoa.domain.repositories.WellnessRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetWellnessListUseCase(
    val scope: CoroutineScope,
    private val wellnessRepository: WellnessRepository
) : UseCase<Unit, List<Wellness>>(scope = scope) {

    override fun run(params: Unit): Flow<List<Wellness>> = when {
        else -> {
            wellnessRepository.getListWellness()
        }
    }


}