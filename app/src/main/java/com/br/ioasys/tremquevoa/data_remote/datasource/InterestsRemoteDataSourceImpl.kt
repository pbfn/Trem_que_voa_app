package com.br.ioasys.tremquevoa.data_remote.datasource

import com.br.ioasys.tremquevoa.data.datasource.remote.InterestsRemoteDataSource
import com.br.ioasys.tremquevoa.data_remote.service.InterestsService
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.util.MockInteresses
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InterestsRemoteDataSourceImpl(
        private val interestsService: InterestsService
):InterestsRemoteDataSource {
    override fun getAllInterests(): Flow<List<Interests>> = flow {
        val response = interestsService.getAllInterests()

        if(response.isSuccessful){
            emit(MockInteresses.listaInteresses)
        }else{
            emit(MockInteresses.listaInteresses)
    }

    }
}