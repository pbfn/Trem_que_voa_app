package com.br.ioasys.tremquevoa.data.datasource.remote

import com.br.ioasys.tremquevoa.domain.model.Disabilities
import kotlinx.coroutines.flow.Flow

interface DisabilitiesRemoteDataSource {

    fun fetchAllDisabilities(token:String): Flow<List<Disabilities>>
}