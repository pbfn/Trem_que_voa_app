package com.br.ioasys.tremquevoa.data_local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.br.ioasys.tremquevoa.data_local.model.EventDataLocal
import com.br.ioasys.tremquevoa.domain.model.Event

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveEvent(event: EventDataLocal)

    @Query("SELECT * FROM events")
    fun getEvents(): Event
}