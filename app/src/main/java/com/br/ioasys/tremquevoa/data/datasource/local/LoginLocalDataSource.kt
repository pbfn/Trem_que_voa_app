package com.br.ioasys.tremquevoa.data.datasource.local

import com.br.ioasys.tremquevoa.domain.model.User

interface LoginLocalDataSource {
    fun saveUser(user: User)
}