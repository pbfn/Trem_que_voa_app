package com.br.ioasys.tremquevoa.data_remote.datasource

import com.br.ioasys.tremquevoa.data.datasource.remote.DisabilitiesRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.model.request.UserDisabilitiesRequest
import com.br.ioasys.tremquevoa.data_remote.service.DisabilitiesService
import com.br.ioasys.tremquevoa.domain.exceptions.RequestException
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DisabilitiesRemoteDataSourceImpl(
    private val disabilitiesService: DisabilitiesService
) : DisabilitiesRemoteDataSource {

    override fun fetchAllDisabilities(token: String): Flow<List<Disabilities>> = flow {
        val response = disabilitiesService.getAllDisabilities(token = "Bearer $token")
        if (response.isSuccessful) {
            response.body()?.let { listReponse ->
                emit(listReponse.toDomain())
            }
        } else {
            emit(throw RequestException())
        }
    }

    override fun fetchDesabilitiesByUser(token: String): Flow<List<Disabilities>> = flow {
        val response = disabilitiesService.getDesabilitiesByUser(token = "Bearer $token")
        if (response.isSuccessful) {
            response.body()?.let {
                emit(it.toDomain())
            }
        }else{
            emit(throw RequestException())
        }
    }

    override fun saveDesabilitiesByUser(
        token: String,
        listIdDisabilities: List<String>
    ): Flow<Boolean> = flow {
        val response = disabilitiesService.saveDesabilitiesByUser(
            token = "Bearer $token",
            userDisabilitiesRequest = UserDisabilitiesRequest(disabilityIds = listIdDisabilities)
        )
        if (response.isSuccessful) {
            response.body()?.let {
                emit(true)
            }
        }else{
            emit(throw RequestException())
        }
    }
}