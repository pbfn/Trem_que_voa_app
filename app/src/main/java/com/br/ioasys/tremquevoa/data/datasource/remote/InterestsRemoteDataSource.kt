package com.br.ioasys.tremquevoa.data.datasource.remote

import com.br.ioasys.tremquevoa.domain.model.Interests
import kotlinx.coroutines.flow.Flow

interface InterestsRemoteDataSource {
    fun fetchAllInterests(token: String): Flow<List<Interests>>

    fun fetchInterestsByUser(token: String): Flow<List<Interests>>

    fun saveInterestsForUser(token: String, listIdInterests: List<String>): Flow<Boolean>
}