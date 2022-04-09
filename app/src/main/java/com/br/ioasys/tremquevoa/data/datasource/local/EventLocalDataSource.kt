package com.br.ioasys.tremquevoa.data.datasource.local

import com.br.ioasys.tremquevoa.domain.model.Event

interface EventLocalDataSource {
    fun saveEvent(event: Event)
}