package com.br.ioasys.tremquevoa.domain.model

class EventAccessibilities(
    val id: String,
    val disabilityId: String,
    val eventId: String,
    val acessibilities: List<Disabilities>
)