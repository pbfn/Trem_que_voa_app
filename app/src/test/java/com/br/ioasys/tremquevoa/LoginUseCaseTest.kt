package com.br.ioasys.tremquevoa

import com.br.ioasys.tremquevoa.domain.exceptions.InvalidEmptyEmailException
import com.br.ioasys.tremquevoa.domain.exceptions.InvalidEmptyPasswordException
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
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
    private lateinit var repository: UserRepository
    private lateinit var subject: LoginUseCase

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
            assert(it == DOMAIN_USER_FAKE)
        }
    }

    @Test(expected = InvalidEmptyPasswordException::class)
    fun `WHEN  EMPTY PASSWORD  MUST RETURN INVALID PASSWORD EMPTY EXCEPTION`() {
        subject.run(
            params = LoginUseCase.Params(
                email = "pbruno@gmail.com",
                password = ""
            )
        )
    }

    @Test(expected = InvalidEmptyEmailException::class)
    fun `WHEN EMPTY EMAIL MUST RETURN INVALID EMAIL EMPTY EXCEPTION`() {
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
            flowOf(DOMAIN_USER_FAKE)
        }
    }

}