package com.todolist.account.adapter.mysql.models

import com.todolist.account.domain.MemberAccount
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
    private var id: String = "",
    private val username: String = "" ,
    private val password: String = "" ,
    private val deleted: Boolean = false,
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private val role: Role = Role.MEMBER
) : Auditable() {
    fun toDomainList(persistModels: List<MemberAccountPersistModel>): List<MemberAccount> {
        return persistModels.map { it.toDomain() }
    }
    fun toDomain() = MemberAccount(
        id = this.id,
        role = this.role,
        username = this.username,
        password = this.password,
        deleted = this.deleted,
        createdTime = this.createdTime,
        createdBy = this.createdBy ?: "",
        updatedTime = this.updatedTime,
        updatedBy = this.updatedBy ?: ""
    )

}
