package com.todolist.account.adapter.mysql.models

import com.todolist.account.domain.enum.Role
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator

@Entity
@Table(name = "member_account")
data class MemberAccountPersistModel(
    @Id @UuidGenerator
    var id: String,
    val username: String,
    val password: String,
    val deleted: Boolean,
    @Enumerated(jakarta.persistence.EnumType.STRING)
    val role: Role = Role.MEMBER
) : Auditable()
