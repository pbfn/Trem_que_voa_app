package com.br.ioasys.tremquevoa.data_remote.datasource

import com.br.ioasys.tremquevoa.data.datasource.remote.InterestsRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.data_remote.model.request.UserInterestsRequest
import com.br.ioasys.tremquevoa.data_remote.service.InterestsService
import com.br.ioasys.tremquevoa.domain.exceptions.RequestException
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.util.MockInteresses
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InterestsRemoteDataSourceImpl(
    private val interestsService: InterestsService
) : InterestsRemoteDataSource {
    override fun fetchAllInterests(token: String): Flow<List<Interests>> = flow {
        val response = interestsService.getAllInterests(token = "Bearer $token")
        if (response.isSuccessful) {
            response.body()?.let { listReponse ->
                emit(listReponse.toDomain())
            }
        } else {
            emit(throw RequestException())
        }

    }

    override fun fetchInterestsByUser(token: String): Flow<List<Interests>> = flow {
        val response = interestsService.getInterestsByUser(token = "Bearer $token")

        if (response.isSuccessful) {
            response.body()?.let {
                emit(it.toDomain())
            }
        } else {
            emit(throw RequestException())
        }
    }

    override fun saveInterestsForUser(
        token: String,
        listIdInterests: List<String>
    ): Flow<Boolean> = flow {
        val response = interestsService.saveInterestsForUser(
            token = "Bearer $token",
            UserInterestsRequest(
                activityIds = listIdInterests
            )
        )
        if (response.isSuccessful) {
            emit(true)
        } else {
            emit(throw RequestException())
        }
    }
}