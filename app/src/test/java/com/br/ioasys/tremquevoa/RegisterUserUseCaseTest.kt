package com.br.ioasys.tremquevoa

import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.domain.repositories.RegisterRepository
import com.br.ioasys.tremquevoa.domain.usecase.RegisterUserUseCase
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

class RegisterUserUseCaseTest {

    @Mock
    private lateinit var repository: RegisterRepository
    private lateinit var subject: RegisterUserUseCase



    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        subject = RegisterUserUseCase(
            registerRepository = repository,
            scope = CoroutineScope(Dispatchers.IO)
        )
    }

    @Test
    fun `WHEN SUCCESS MUST RETURN USER`() = runBlocking {
        stubOnSuccess()
        subject.run(
            params = RegisterUserUseCase.Params(
                firstName = "firstName",
                lastName = "lastName",
                email = "email",
                password = "password",
                passwordConfirmation = "password"
            )
        ).collect {
            assert(it == DOMAIN_USER_FAKE)
        }
    }

    @Test(expected = InvalidEmptyEmailException::class)
    fun `WHEN EMPTY EMAIL MUST RETURN INVALID EMPTY EMAIL EXCEPTION`() {
        subject.run(
            params = RegisterUserUseCase.Params(
                email = "",
                firstName = "firstName",
                lastName = "lastName",
                password = "password",
                passwordConfirmation = "password"
            )
        )
    }

    @Test(expected = InvalidEmptyFirstNameException::class)
    fun `WHEN EMPTY FIRST NAME MUST RETURN INVALID EMPTY FIRST NAME EXCEPTION`() {
        subject.run(
            params = RegisterUserUseCase.Params(
                email = "email",
                firstName = "",
                lastName = "lastName",
                password = "password",
                passwordConfirmation = "password"
            )
        )
    }

    @Test(expected = InvalidEmptyLastNameException::class)
    fun `WHEN EMPTY LAST NAME MUST RETURN INVALID EMPTY LAST NAME EXCEPTION`() {
        subject.run(
            params = RegisterUserUseCase.Params(
                email = "email",
                firstName = "firstName",
                lastName = "",
                password = "password",
                passwordConfirmation = "password"
            )
        )
    }

    @Test(expected = InvalidEmptyPasswordException::class)
    fun `WHEN EMPTY PASSWORD MUST RETURN INVALID EMPTY PASSWORD EXCEPTION`() {
        subject.run(
            params = RegisterUserUseCase.Params(
                email = "email",
                firstName = "firstName",
                lastName = "lastName",
                password = "",
                passwordConfirmation = "password"
            )
        )
    }


    @Test(expected = InvalidEmptyPasswordConfirmException::class)
    fun `WHEN EMPTY CONFIRM PASSWORD MUST RETURN INVALID EMPTY CONFIRMPASSWORD EXCEPTION`() {
        subject.run(
            params = RegisterUserUseCase.Params(
                email = "email",
                firstName = "firstName",
                lastName = "lastName",
                password = "password",
                passwordConfirmation = ""
            )
        )
    }

    @Test(expected = InvalidDifferPasswordException::class)
    fun `WHEN PASSWORD DIFFER CONFIRM PASSWORD MUST RETURN INVALID DIFFER PASSWORD EXCEPTION`() {
        subject.run(
            params = RegisterUserUseCase.Params(
                email = "email",
                firstName = "firstName",
                lastName = "lastName",
                password = "password",
                passwordConfirmation = "passwordConfirmation"
            )
        )
    }

    private fun stubOnSuccess() {
        whenever(
            repository.registerUser(
                firstName = any(),
                lastName = any(),
                email = any(),
                password = any(),
                passwordConfirmation = any()
            )
        ).thenAnswer {
            flowOf(DOMAIN_USER_FAKE)
        }
    }
}