package com.br.ioasys.tremquevoa.data.datasource.remote

import com.br.ioasys.tremquevoa.domain.model.Disabilities
import kotlinx.coroutines.flow.Flow

interface DisabilitiesRemoteDataSource {

    fun fetchAllDisabilities(token: String): Flow<List<Disabilities>>

    fun fetchDesabilitiesByUser(token: String): Flow<List<Disabilities>>

    fun saveDesabilitiesByUser( token: String, listIdDisabilities: List<String>): Flow<Boolean>
}