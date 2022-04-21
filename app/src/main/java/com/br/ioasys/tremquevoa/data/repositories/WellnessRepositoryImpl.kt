package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.local.UserLocalDataSource
import com.br.ioasys.tremquevoa.data.datasource.remote.WellnessRemoteDataSource
import com.br.ioasys.tremquevoa.domain.exceptions.EmptyToken
import com.br.ioasys.tremquevoa.domain.model.Wellness
import com.br.ioasys.tremquevoa.domain.repositories.WellnessRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class WellnessRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val wellnessRemoteDataSource: WellnessRemoteDataSource
) : WellnessRepository {
    override fun getListWellness(): Flow<List<Wellness>> = flow {
        userLocalDataSource.getToken().collect { token ->
            if (token.isNotEmpty()) {
                wellnessRemoteDataSource.getListWellness(token = token).collect {
                    emit(it)
                }
            } else {
                emit(throw EmptyToken())
            }
        }
    }
}