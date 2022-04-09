package com.br.ioasys.tremquevoa.data_local.datasource

import com.br.ioasys.tremquevoa.data.datasource.local.EventLocalDataSource
import com.br.ioasys.tremquevoa.data_local.database.EventDao
import com.br.ioasys.tremquevoa.data_local.mappers.toDao
import com.br.ioasys.tremquevoa.domain.model.Event

class EventLocalDataSourceImpl(
    private val eventDao: EventDao
): EventLocalDataSource {
    override fun saveEvent(event: Event) = eventDao.saveEvent(
        event = event.toDao()
    )
}