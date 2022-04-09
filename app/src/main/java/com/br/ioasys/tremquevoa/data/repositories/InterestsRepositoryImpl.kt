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

    override fun fetchAllInterests(token: String): Flow<List<Interests>> = flow {
        interestsRemoteDataSource.fetchAllInterests(token = token).collect { listInterests ->
            emit(listInterests)
        }
    }

    override fun fetchInterestsByUser(token: String): Flow<List<Interests>> = flow {
        interestsRemoteDataSource.fetchInterestsByUser(token = token).collect { listInterests ->
            emit(listInterests)
        }
    }

    override fun saveInterestsForUser(
        token: String,
        listIdInterests: List<String>
    ): Flow<Boolean> = flow {
        interestsRemoteDataSource.saveInterestsForUser(
            token = token,
            listIdInterests = listIdInterests
        ).collect {
            emit(it)
        }
    }
}