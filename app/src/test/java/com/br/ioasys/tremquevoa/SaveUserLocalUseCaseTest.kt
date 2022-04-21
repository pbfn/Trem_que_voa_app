package com.br.ioasys.tremquevoa

import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class SaveUserLocalUseCaseTest {

    @Mock
    private lateinit var repository: UserRepository
    private lateinit var subject: SaveUserLocalUseCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        subject = SaveUserLocalUseCase(
            loginRepository = repository,
            scope = CoroutineScope(Dispatchers.IO)
        )
    }

    @Test
    fun `WHEN SUCCESS MUST RETURN TRUE`() = runBlocking {
        stubOnSuccess()
        subject.run(
            params = SaveUserLocalUseCase.Params(
                user = DOMAIN_USER_FAKE
            )
        ).collect {
            assert(true)
        }
    }

    @Test(expected = InvalidEmptyIdException::class)
    fun `WHEN EMPTY ID MUST RETURN INVALID EMPTY ID EXCEPTION`() {
        subject.run(
            params = SaveUserLocalUseCase.Params(
                user = DOMAIN_USER_FAKE_ID
            )
        )
    }

    @Test(expected = InvalidEmptyEmailException::class)
    fun `WHEN EMPTY EMAIL MUST RETURN INVALID EMPTY EMAIL EXCEPTION`() {
        subject.run(
            params = SaveUserLocalUseCase.Params(
               user = DOMAIN_USER_FAKE_EMAIL
            )
        )
    }

    @Test(expected = InvalidEmptyFirstNameException::class)
    fun `WHEN EMPTY FIRST NAME MUST RETURN INVALID EMPTY FIRST NAME EXCEPTION`() {
        subject.run(
            params = SaveUserLocalUseCase.Params(
                user = DOMAIN_USER_FAKE_FIRSTNAME
            )
        )
    }

    @Test(expected = InvalidEmptyLastNameException::class)
    fun `WHEN EMPTY LAST NAME MUST RETURN INVALID EMPTY LAST NAME EXCEPTION`() {
        subject.run(
            params = SaveUserLocalUseCase.Params(
                user = DOMAIN_USER_FAKE_LASTNAME
            )
        )
    }

    @Test(expected = InvalidEmptyTokenException::class)
    fun `WHEN EMPTY TOKEN MUST RETURN INVALID EMPTY TOKEN EXCEPTION`() {
        subject.run(
            params = SaveUserLocalUseCase.Params(
                user = DOMAIN_USER_FAKE_TOKEN
            )
        )
    }

    @Test(expected = InvalidEmptyRefreshTokenException::class)
    fun `WHEN EMPTY  REFRESH TOKEN MUST RETURN INVALID EMPTY RERESH TOKEN EXCEPTION`() {
        subject.run(
            params = SaveUserLocalUseCase.Params(
                user = DOMAIN_USER_FAKE_REFRESHTOKEN
            )
        )
    }


    private fun stubOnSuccess() {
        whenever(
            repository.saveUser(user = any())
        ).thenAnswer {
            Unit
        }
    }
}