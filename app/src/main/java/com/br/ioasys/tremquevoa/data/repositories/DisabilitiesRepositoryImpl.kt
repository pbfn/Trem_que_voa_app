package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.local.UserLocalDataSource
import com.br.ioasys.tremquevoa.data.datasource.remote.DisabilitiesRemoteDataSource
import com.br.ioasys.tremquevoa.domain.exceptions.EmptyToken
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.repositories.DisabilitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class DisabilitiesRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val disabilitiesRemoteDataSource: DisabilitiesRemoteDataSource
) : DisabilitiesRepository {

    override fun fetchAllDesabilities(): Flow<List<Disabilities>> = flow {
        userLocalDataSource.getToken().collect { token ->
            if (token.isNotEmpty()) {
                disabilitiesRemoteDataSource.fetchAllDisabilities(token = token)
                    .collect { listDisabilities ->
                        emit(listDisabilities)
                    }
            } else {
                emit(throw EmptyToken())
            }
        }
    }

    override fun fetchDesabilitiesByUser(): Flow<List<Disabilities>> = flow {
        userLocalDataSource.getToken().collect { token ->
            if (token.isNotEmpty()) {
                disabilitiesRemoteDataSource.fetchDesabilitiesByUser(token = token)
                    .collect { listDisabilities ->
                        emit(listDisabilities)
                    }
            } else {
                emit(throw EmptyToken())
            }
        }

    }

    override fun saveDesabilitiesByUser(
        listIdDisabilities: List<String>
    ): Flow<Boolean> = flow {
        userLocalDataSource.getToken().collect { token ->
            if (token.isNotEmpty()) {
                disabilitiesRemoteDataSource.saveDesabilitiesByUser(
                    token = token,
                    listIdDisabilities = listIdDisabilities
                ).collect {
                    emit(it)
                }
            } else {
                emit(throw EmptyToken())
            }
        }

    }
}