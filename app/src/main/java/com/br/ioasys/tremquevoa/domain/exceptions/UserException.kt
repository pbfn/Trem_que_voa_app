package com.br.ioasys.tremquevoa.domain.exceptions

open class UserException : Exception()

class InvalidFirstNameException : UserException()
class InvalidLastNameException : UserException()
class InvalidEmailException : UserException()
class InvalidPasswordException : UserException()
class InvalidPasswordConfirmException : UserException()
class InvalidDifferPasswordException : UserException()
