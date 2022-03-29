package com.br.ioasys.tremquevoa.domain.exceptions

open class UserException : Exception()

class InvalidEmptyFirstNameException : UserException()
class InvalidEmptyLastNameException : UserException()
class InvalidEmptyEmailException : UserException()
class InvalidEmptyPasswordException : UserException()
class InvalidEmptyPasswordConfirmException : UserException()
class InvalidDifferPasswordException : UserException()
class InvalidRegisterException : UserException()
class IvalidLoginException : UserException()