package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.repositories.LoginRepository
import com.br.ioasys.tremquevoa.domain.repositories.RegisterEventRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class RegisterEventUseCase(
    private val loginRepository: LoginRepository,
    private val registerEventRepository: RegisterEventRepository,
    scope: CoroutineScope
) : UseCase<RegisterEventUseCase.Params, Event>(scope = scope) {

    data class Params(
        val name: String,
        val description: String,
        val isOnline: Boolean,
        val url: String,
        val date: String,
        val isPetFriendly: Boolean,
        val maxParticipants: Int,
        val startTime: String,
        val endTime: String,
        val activityId: String,
        val userId: String,
        val userIdentity: String,
        val accessibilities: String,
        val address: String
    )

    override fun run(params: Params): Flow<Event> {

        params.apply {
            var userId = getUserId()
        }

        validateFields(params)

        return registerEventRepository.registerEvent(
            name = params.name,
            description = params.description,
            isOnline = params.isOnline,
            url = params.url,
            date = params.date,
            isPetFriendly = params.isPetFriendly,
            maxParticipants = params.maxParticipants,
            startTime = params.startTime,
            endTime = params.endTime,
            activityId = params.activityId,
            userId = params.userId,
            userIdentity = params.userIdentity,
            accessibilities = params.accessibilities,
            address = params.address
        )
    }

    private fun getUserId(): String {
            return loginRepository.fetchUserLogged().map { user ->
                user?.id ?: ""
            }.toString()
    }

    private fun validateFields(params: Params) {
        if (params.name.isEmpty()) {
            throw InvalidEmptyNameException()
        }
        if (params.description.isEmpty()) {
            throw InvalidEmptyDescriptionException()
        }
        if (params.date.isEmpty()) {
            throw InvalidEmptyDateException()
        }
        if (params.userId.isEmpty()) {
            throw InvalidEmptyUserIdException()
        }
        if (params.url.isEmpty()) {
            throw InvalidEmptyMinimumAgeException()
        }
        if (params.maxParticipants.toString().isEmpty()) {
            throw InvalidEmptyMaxParticipantsException()
        }
        if (params.startTime.isEmpty()) {
            throw InvalidEmptyStartTimeException()
        }
        if (params.endTime.isEmpty()) {
            throw InvalidEmptyEndTimeException()
        }
        if (params.activityId.isEmpty()) {
            throw InvalidEmptyActivityIdException()
        }
        if (params.userIdentity.isEmpty()) {
            throw InvalidEmptyUserIdentityException()
        }
    }
}