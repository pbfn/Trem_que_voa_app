package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.remote.DisabilitiesRemoteDataSource
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.repositories.DisabilitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class DisabilitiesRepositoryImpl(
    private val disabilitiesRemoteDataSource: DisabilitiesRemoteDataSource
): DisabilitiesRepository {
    override fun fetchAllDesabilities(): Flow<List<Disabilities>> = flow {
        disabilitiesRemoteDataSource.fetchAllDisabilities().collect { listDisabilities ->
            emit(listDisabilities)
        }
    }
}