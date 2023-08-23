package com.todolist.account.application.models

data class CreateMemberCommand (
    val username: String,
    val password: String
)
