package com.br.ioasys.tremquevoa.domain.model

data class Interests(
    val id: String,
    val title: String,
    var selected: Boolean = false,
    var urlActive: String,
    var urlInactive: String
)
