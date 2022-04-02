package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.remote.InterestsRemoteDataSource
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.repositories.InterestsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class InterestsRepositoryImpl(
    private val interestsRemoteDataSource: InterestsRemoteDataSource
) : InterestsRepository {

    override fun getAllInterests(): Flow<List<Interests>> = flow {
        interestsRemoteDataSource.getAllInterests().collect { listInterests ->
            emit(listInterests)
        }
    }
}