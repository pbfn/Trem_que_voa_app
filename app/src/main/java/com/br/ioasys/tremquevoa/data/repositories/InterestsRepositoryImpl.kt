package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.local.UserLocalDataSource
import com.br.ioasys.tremquevoa.data.datasource.remote.InterestsRemoteDataSource
import com.br.ioasys.tremquevoa.domain.exceptions.EmptyToken
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.repositories.InterestsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class InterestsRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val interestsRemoteDataSource: InterestsRemoteDataSource
) : InterestsRepository {

    override fun fetchAllInterests(): Flow<List<Interests>> = flow {
        userLocalDataSource.getToken().collect { token ->
            if (token.isNotEmpty()) {
                interestsRemoteDataSource.fetchAllInterests(token = token)
                    .collect { listInterests ->
                        emit(listInterests)
                    }
            } else {
                emit(throw EmptyToken())
            }
        }
    }

    override fun fetchInterestsByUser(): Flow<List<Interests>> = flow {
        userLocalDataSource.getToken().collect { token ->
            if (token.isNotEmpty()) {
                interestsRemoteDataSource.fetchInterestsByUser(token = token)
                    .collect { listInterests ->
                        emit(listInterests)
                    }
            } else {
                emit(throw EmptyToken())
            }
        }

    }

    override fun saveInterestsForUser(
        listIdInterests: List<String>
    ): Flow<Boolean> = flow {
        userLocalDataSource.getToken().collect { token ->
            if (token.isNotEmpty()) {
                interestsRemoteDataSource.saveInterestsForUser(
                    token = token,
                    listIdInterests = listIdInterests
                ).collect {
                    emit(it)
                }
            } else {
                emit(throw EmptyToken())
            }
        }

    }
}