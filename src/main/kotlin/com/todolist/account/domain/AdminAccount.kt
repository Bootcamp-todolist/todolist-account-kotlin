package com.todolist.account.domain

import com.todolist.account.domain.enum.Role
import java.time.Instant

data class AdminAccount(

    val id: String = "",

    val role: Role = Role.ADMIN,

    val username: String,

    val password: String,

    val createdTime: Instant,

    val createdBy: String,

    val updatedTime: Instant,

    val updatedBy: String
)
