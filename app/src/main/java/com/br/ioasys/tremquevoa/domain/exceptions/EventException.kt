package com.br.ioasys.tremquevoa.domain.exceptions

open class EventException : Exception()

class InvalidEmptyNameException : EventException()
class InvalidEmptyDescriptionException : EventException()
class InvalidEmptyDateException : EventException()
class InvalidEmptyMaxParticipantsException : EventException()
class InvalidEmptyStartTimeException : EventException()
class InvalidEmptyEndTimeException : EventException()
class InvalidEmptyActivityIdException : EventException()
class InvalidEmptyUserIdException : EventException()
class InvalidEmptyUserIdentityException : EventException()
class InvalidEmptyPriceException : EventException()
