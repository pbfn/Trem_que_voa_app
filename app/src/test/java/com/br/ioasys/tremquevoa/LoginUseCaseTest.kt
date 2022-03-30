package com.br.ioasys.tremquevoa

import com.br.ioasys.tremquevoa.data_remote.mappers.toDomain
import com.br.ioasys.tremquevoa.domain.exceptions.InvalidEmptyEmailException
import com.br.ioasys.tremquevoa.domain.exceptions.InvalidEmptyPasswordException
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.LoginRepository
import com.br.ioasys.tremquevoa.domain.usecase.LoginUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class LoginUseCaseTest {


    @Mock
    private lateinit var repository: LoginRepository
    private lateinit var subject: LoginUseCase


    private val userFake = User(
        id = "123456",
        email = "pbruno@gmail.com",
        firstName = "Pedro",
        lastName = "Bruno",
        token = "token",
        refreshToken = "refreshToken"
    )

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        subject = LoginUseCase(
            loginRepository = repository,
            scope = CoroutineScope(Dispatchers.IO)
        )
    }

    @Test
    fun `WHEN SUCCESS MUST RETURN USER`() = runBlocking {
        stubOnSuccess()
        subject.run(
            params = LoginUseCase.Params(
                email = "email",
                password = "password"
            )
        ).collect {
            assert(it == userFake)
        }
    }

    @Test(expected = InvalidEmptyPasswordException::class)
    fun `WHEN  EMPTY PASSWORD  MUST RETURN INVALIDPASSWORDEMPTYEXCEPTION`() {
        subject.run(
            params = LoginUseCase.Params(
                email = "pbruno@gmail.com",
                password = ""
            )
        )
    }

    @Test(expected = InvalidEmptyEmailException::class)
    fun `WHEN EMPTY EMAIL MUST RETURN INVALIDEMAILEMPTYEXCEPTION`() {
        subject.run(
            params = LoginUseCase.Params(
                email = "",
                password = ""
            )
        )
    }


    private fun stubOnSuccess() {
        whenever(
            repository.doLogin(
                email = any(),
                password = any()
            )
        ).thenAnswer {
            flowOf(userFake)
        }
    }

}