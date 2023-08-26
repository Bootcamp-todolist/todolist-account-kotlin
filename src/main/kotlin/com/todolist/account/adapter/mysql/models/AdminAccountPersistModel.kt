package com.todolist.account.adapter.mysql.models

import com.todolist.account.domain.enum.Role
import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator


@Entity
@Table(name = "admin_account")
data class AdminAccountPersistModel(
    @Id
    @UuidGenerator
    var id: String,
    val username: String,
    val password: String,
    @Enumerated(EnumType.STRING)
    val role: Role = Role.ADMIN
) : Auditable()
