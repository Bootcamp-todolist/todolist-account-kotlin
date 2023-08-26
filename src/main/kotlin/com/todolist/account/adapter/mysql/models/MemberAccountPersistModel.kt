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
    private var id: String,
    private val username: String,
    private val password: String,
    private val deleted: Boolean,
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private val role: Role = Role.MEMBER
) : Auditable() {

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

    companion object {
        fun fromDomain(domain: MemberAccount): MemberAccountPersistModel {
            val persistModel = MemberAccountPersistModel(
                id = domain.id,
                role = domain.role,
                username = domain.username,
                password = domain.password,
                deleted = domain.deleted
            )
            persistModel.createdBy = domain.createdBy
            persistModel.updatedBy = domain.updatedBy
            return persistModel
        }
    }

}
