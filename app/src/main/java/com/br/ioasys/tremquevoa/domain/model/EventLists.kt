package com.br.ioasys.tremquevoa.domain.model

data class EventLists(
    val listOnline: List<Event>,
    val listPromoted: List<Event>,
    val listRecommended: List<Event>?=null
)

