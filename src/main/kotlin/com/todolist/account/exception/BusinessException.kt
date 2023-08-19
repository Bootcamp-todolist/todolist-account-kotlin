package com.todolist.account.exception

import org.springframework.http.HttpStatus

class BusinessException : RuntimeException {

    val httpStatus: HttpStatus
    val errorMessage: ErrorMessage
    constructor(errorMessage: ErrorMessage) : this(errorMessage, HttpStatus.BAD_REQUEST)

    constructor(errorMessage: ErrorMessage, httpStatus: HttpStatus) : this(errorMessage, httpStatus, errorMessage.value)

    constructor(errorMessage: ErrorMessage, httpStatus: HttpStatus, message: String) : super(message) {
        this.errorMessage = errorMessage
        this.httpStatus = httpStatus
    }

}
