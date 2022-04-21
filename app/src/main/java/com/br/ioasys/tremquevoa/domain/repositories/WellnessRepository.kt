package com.br.ioasys.tremquevoa.domain.repositories

import com.br.ioasys.tremquevoa.domain.model.Wellness
import kotlinx.coroutines.flow.Flow

interface WellnessRepository {

    fun getListWellness(): Flow<List<Wellness>>

}