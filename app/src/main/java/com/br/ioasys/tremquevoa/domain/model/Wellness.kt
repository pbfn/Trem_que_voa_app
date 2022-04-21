package com.br.ioasys.tremquevoa.domain.model

import java.io.Serializable

data class Wellness(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String
) : Serializable