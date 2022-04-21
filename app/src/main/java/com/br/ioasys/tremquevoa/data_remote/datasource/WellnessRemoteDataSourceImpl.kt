package com.br.ioasys.tremquevoa.data_remote.datasource

import com.br.ioasys.tremquevoa.data.datasource.remote.WellnessRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.service.WellnessService
import com.br.ioasys.tremquevoa.domain.model.Wellness
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WellnessRemoteDataSourceImpl(
    private val wellnessService: WellnessService
) : WellnessRemoteDataSource {
    override fun getListWellness(token: String): Flow<List<Wellness>> = flow {
        val response = wellnessService.getListWellness(token = "Bearer $token")

        if (response.isSuccessful) {
            response.body()?.let {
                emit(it.toDomain())
            }
        } else {

        }

    }
}