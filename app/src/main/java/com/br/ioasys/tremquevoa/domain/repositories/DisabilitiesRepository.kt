package com.br.ioasys.tremquevoa.domain.repositories

import com.br.ioasys.tremquevoa.domain.model.Disabilities
import kotlinx.coroutines.flow.Flow

interface DisabilitiesRepository {

    fun fetchAllDesabilities(): Flow<List<Disabilities>>
    fun fetchDesabilitiesByUser(): Flow<List<Disabilities>>
    fun saveDesabilitiesByUser(listIdDisabilities: List<String>): Flow<Boolean>
}