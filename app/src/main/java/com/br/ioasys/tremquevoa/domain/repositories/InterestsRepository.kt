package com.br.ioasys.tremquevoa.domain.repositories

import com.br.ioasys.tremquevoa.domain.model.Interests
import kotlinx.coroutines.flow.Flow

interface InterestsRepository {

    fun fetchAllInterests(): Flow<List<Interests>>

    fun saveInterestsForUser(token:String,listIdInterests: List<String>):Flow<Boolean>
}