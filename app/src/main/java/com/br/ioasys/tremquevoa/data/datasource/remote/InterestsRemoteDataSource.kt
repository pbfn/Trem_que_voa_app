package com.br.ioasys.tremquevoa.data.datasource.remote

import com.br.ioasys.tremquevoa.domain.model.Interests
import kotlinx.coroutines.flow.Flow

interface InterestsRemoteDataSource {
    fun getAllInterests(): Flow<List<Interests>>
}