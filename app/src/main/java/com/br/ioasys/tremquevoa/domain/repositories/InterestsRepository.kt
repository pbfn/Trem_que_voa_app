package com.br.ioasys.tremquevoa.domain.repositories

import com.br.ioasys.tremquevoa.domain.model.Interests
import kotlinx.coroutines.flow.Flow

interface InterestsRepository {

    fun getAllInterests(): Flow<List<Interests>>
}