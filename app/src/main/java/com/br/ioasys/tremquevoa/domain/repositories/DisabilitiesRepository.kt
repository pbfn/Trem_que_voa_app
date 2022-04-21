package com.br.ioasys.tremquevoa.domain.repositories

import com.br.ioasys.tremquevoa.domain.model.Disabilities
import kotlinx.coroutines.flow.Flow

interface DisabilitiesRepository {

    fun fetchAllDesabilities(token: String): Flow<List<Disabilities>>
    fun fetchDesabilitiesByUser(token: String): Flow<List<Disabilities>>
    fun saveDesabilitiesByUser(token: String,listIdDisabilities: List<String>): Flow<Boolean>
}