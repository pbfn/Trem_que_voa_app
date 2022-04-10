package com.br.ioasys.tremquevoa.data_remote.datasource

import com.br.ioasys.tremquevoa.data.datasource.remote.DisabilitiesRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.service.DisabilitiesService
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DisabilitiesRemoteDataSourceImpl(
    private val disabilitiesService: DisabilitiesService
): DisabilitiesRemoteDataSource {

    override fun fetchAllDisabilities(token:String): Flow<List<Disabilities>> = flow {
        val response = disabilitiesService.getAllDisabilities(token = "Bearer $token")
        if (response.isSuccessful) {
            response.body()?.let { listReponse ->
                emit(listReponse.toDomain())
            }
        } else {
           emit(error(response.code()))
        }
    }
}