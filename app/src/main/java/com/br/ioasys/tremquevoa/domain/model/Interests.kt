package com.br.ioasys.tremquevoa.domain.model

data class Interests(
    val id: String,
    val title: String,
    val imageUrl: String,
    val selected: Boolean = false
)
