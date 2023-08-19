package com.todolist.account.exception


data class ErrorResult(
    val errorKey: String,
    val errorMsg: String
) {
    constructor(error: ErrorMessage) : this(error.value, error.value)
}

