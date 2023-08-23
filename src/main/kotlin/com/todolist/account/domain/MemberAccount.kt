package com.todolist.account.domain

import com.todolist.account.domain.enum.Role
import java.time.Instant

data class MemberAccount (

    val id: String = "",

    val role: Role = Role.MEMBER,

    val username: String = "",

    val password: String = "",

    val deleted: Boolean = false,

    val createdTime: Instant = Instant.now(),

    val createdBy: String = "",

    val updatedTime: Instant = Instant.now(),

    val updatedBy: String = ""
)
