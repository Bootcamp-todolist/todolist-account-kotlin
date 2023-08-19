package com.todolist.account.adapter.mysql.models

import com.todolist.account.domain.AdminAccount
import com.todolist.account.domain.enum.Role
import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator


@Entity
@Table(name = "admin_account")
data class AdminAccountPersistModel(
    @Id
    @UuidGenerator
    var id: String = "",
    val username: String = "",
    val password: String = "",
    @Enumerated(EnumType.STRING)
    val role: Role = Role.ADMIN
) : Auditable() {
    fun toDomain() = AdminAccount(
        id = this.id,
        role = this.role,
        username = this.username,
        password = this.password,
        createdTime = this.createdTime,
        createdBy = this.createdBy ?: "",
        updatedTime = this.updatedTime,
        updatedBy = this.updatedBy ?: ""
    )

    fun fromDomain(domain: AdminAccount): AdminAccountPersistModel {
        val persistModel = AdminAccountPersistModel(
            id = domain.id,
            role = domain.role,
            username = domain.username,
            password = domain.password
        )
        persistModel.createdBy = domain.createdBy
        persistModel.updatedBy = domain.updatedBy
        return persistModel
    }
}
