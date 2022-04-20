package com.br.ioasys.tremquevoa.data_remote.mappers

import com.br.ioasys.tremquevoa.data_remote.model.response.DailyMessageReponse
import com.br.ioasys.tremquevoa.domain.model.Message

fun DailyMessageReponse.toDomain() = Message(
    id = this.id,
    text = this.text
)