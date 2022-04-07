package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.repositories.RegisterEventRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class RegisterEventUseCase(
    private val userRepository: UserRepository,
    private val registerEventRepository: RegisterEventRepository,
    scope: CoroutineScope
) : UseCase<RegisterEventUseCase.Params, Event>(scope = scope) {

    data class Params(
        val name: String,
        val description: String,
        val isOnline: Boolean,
        val date: String,
        val minimumAge: Int,
        val maxParticipants: Int,
        val startTime: String,
        val endTime: String,
        val activityId: String,
        var userId: String,
        val userIdentity: String,
        val isAccessible: Boolean
    )

    override fun run(params: Params): Flow<Event> {

        params.apply {
            userId = getUserId()
        }

        validateFields(params)

        return registerEventRepository.registerEvent(
            name = params.name,
            description = params.description,
            isOnline = params.isOnline,
            date = params.date,
            minimumAge = params.minimumAge,
            maxParticipants = params.maxParticipants,
            startTime = params.startTime,
            endTime = params.endTime,
            activityId = params.activityId,
            userId = params.userId,
            userIdentity = params.userIdentity,
            isAccessible = params.isAccessible,
        )
    }

    private fun getUserId(): String {
            return userRepository.fetchUserLogged().map { user ->
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
        if (params.minimumAge.toString().isEmpty()) {
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