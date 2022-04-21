package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.remote.DisabilitiesRemoteDataSource
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.repositories.DisabilitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class DisabilitiesRepositoryImpl(
    private val disabilitiesRemoteDataSource: DisabilitiesRemoteDataSource
) : DisabilitiesRepository {
    override fun fetchAllDesabilities(token: String): Flow<List<Disabilities>> = flow {
        disabilitiesRemoteDataSource.fetchAllDisabilities(token = token)
            .collect { listDisabilities ->
                emit(listDisabilities)
            }
    }

    override fun fetchDesabilitiesByUser(token: String): Flow<List<Disabilities>> = flow {
        disabilitiesRemoteDataSource.fetchDesabilitiesByUser(token = token)
            .collect { listDisabilities ->
                emit(listDisabilities)
            }
    }

    override fun saveDesabilitiesByUser(
        token: String,
        listIdDisabilities: List<String>
    ): Flow<Boolean> = flow {
        disabilitiesRemoteDataSource.saveDesabilitiesByUser(
            token = token,
            listIdDisabilities = listIdDisabilities
        ).collect {
            emit(it)
        }
    }
}