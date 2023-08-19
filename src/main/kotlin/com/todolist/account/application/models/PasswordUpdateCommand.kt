package com.todolist.account.application.models

data class PasswordUpdateCommand (
    val oldPassword: String,
    val newPassword: String
)
