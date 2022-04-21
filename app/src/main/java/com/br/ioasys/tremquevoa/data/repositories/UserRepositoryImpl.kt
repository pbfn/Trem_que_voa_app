package com.br.ioasys.tremquevoa.data.repositories

import com.br.ioasys.tremquevoa.data.datasource.local.UserLocalDataSource
import com.br.ioasys.tremquevoa.data.datasource.remote.UserRemoteDataSource
import com.br.ioasys.tremquevoa.domain.exceptions.EmptyToken
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {

    override fun doLogin(email: String, password: String): Flow<User> =
        flow {
            userRemoteDataSource.doLogin(
                email = email,
                password = password
            ).collect { user ->
                userLocalDataSource.saveToken(
                    token = user.token,
                )
                emit(user)
            }
        }


    override fun registerUser(
        firstName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<User> = flow {
        userRemoteDataSource.registerUser(
            firstName = firstName,
            email = email,
            password = password,
            passwordConfirmation = passwordConfirmation
        ).collect { user ->
            emit(user)
        }
    }

    override fun updateEmergencyContactsUser(
        emergencyName: String,
        emergencyPhone: String
    ): Flow<User> = flow {
        userLocalDataSource.getToken().collect { token ->
            if (token.isNotEmpty()) {
                userRemoteDataSource.updateEmergencyContactsUser(
                    token = token,
                    emergencyName = emergencyName,
                    emergencyPhone = emergencyPhone
                ).collect {
                    emit(it)
                }
            } else {
                emit(throw EmptyToken())
            }
        }

    }


    override fun resetPassword(email: String): Flow<Boolean> = flow {
        userRemoteDataSource.resetPassword(email = email).collect {
            emit(it)
        }
    }

    override fun updateAboutMe(aboutMe: String): Flow<User> = flow {
        userLocalDataSource.getToken().collect { token ->
            if (token.isNotEmpty()) {
                userRemoteDataSource.updateAboutMeUser(
                    token = token,
                    aboutMe = aboutMe
                ).collect {
                    emit(it)
                }
            } else {
                emit(throw EmptyToken())
            }
        }

    }

    override fun verifyFirstLogin(): Flow<Boolean> = flow {
        userLocalDataSource.verifyFirstLogin().collect {
            emit(it)
        }
    }

    override fun verifyMaintainLogin(): Flow<Boolean> = flow {
        userLocalDataSource.verifyMaintainLogin().collect {
            emit(it)
        }
    }

    override fun setFirstLogin() {
        userLocalDataSource.setFirstLogin()
    }

    override fun setMaintainLogin() {
        userLocalDataSource.setMaintainLogin()
    }

    override fun saveDateLogin(date: String): Flow<String> = flow {
        userLocalDataSource.saveDateLogin(date = date).collect {
            emit(it)
        }
    }

    override fun getUser(): Flow<User> = flow {
        userLocalDataSource.getToken().collect { token ->
            if (token.isNotEmpty()) {
                userRemoteDataSource.getUser(token = token).collect {
                    emit(it)
                }
            } else {
                emit(throw EmptyToken())
            }
        }
    }

}