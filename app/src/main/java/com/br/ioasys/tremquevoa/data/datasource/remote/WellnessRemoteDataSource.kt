package com.br.ioasys.tremquevoa.data.datasource.remote

import com.br.ioasys.tremquevoa.domain.model.Wellness
import kotlinx.coroutines.flow.Flow

interface WellnessRemoteDataSource {
    fun getListWellness(token: String): Flow<List<Wellness>>
}