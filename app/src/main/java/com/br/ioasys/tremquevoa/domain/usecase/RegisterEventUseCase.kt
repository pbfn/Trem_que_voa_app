package com.br.ioasys.tremquevoa.domain.usecase

import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.repositories.UserRepository
import com.br.ioasys.tremquevoa.domain.repositories.EventRepository
import com.br.ioasys.tremquevoa.domain.usecase.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class RegisterEventUseCase(
    private val eventRepository: EventRepository,
    scope: CoroutineScope
) : UseCase<RegisterEventUseCase.Params, Unit>(scope = scope) {

    data class Params(
        val name: String,
        val description: String,
        val isOnline: Boolean,
        val url: String,
        var date: String,
        val isPetFriendly: Boolean,
        val maxParticipants: Int,
        var startTime: String,
        var endTime: String,
        val activityId: String,
        val price: Int,
        val userId: String? = "",
        val userIdentity: String,
        val accessibilities: List<String>,
        val street: String,
        val number: Int,
        val city: String,
        val state: String,
        val zipCode: String,
        val referencePoint: String,
    )

    override fun run(params: Params): Flow<Unit> {

        validateFields(params)

        return eventRepository.registerEvent(
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
            price = params.price,
            userId = params.userId?:"",
            userIdentity = params.userIdentity,
            accessibilities = params.accessibilities,
            street = params.street,
            number = params.number,
            city = params.city,
            state = params.state,
            zipCode = params.zipCode,
            referencePoint = params.referencePoint,
        )
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
        if (params.price.toString().isEmpty()) {
            throw InvalidEmptyPriceException()
        }
    }
}