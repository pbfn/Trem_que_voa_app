package com.br.ioasys.tremquevoa.domain.model

data class Disabilities (
    val id: String,
    val name: String,
    val description: String,
    val active: Boolean = true
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Disabilities

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}