package com.todolist.account.adapter.mysql.mapper

import com.todolist.account.adapter.mysql.models.MemberAccountPersistModel
import com.todolist.account.domain.MemberAccount

abstract class MemberAccountMapper {
    companion object {
        fun toDomain(persistModel: MemberAccountPersistModel): MemberAccount {
            return MemberAccount(
                id = persistModel.id,
                role = persistModel.role,
                username = persistModel.username,
                password = persistModel.password,
                deleted = persistModel.deleted,
                createdTime = persistModel.createdTime,
                createdBy = persistModel.createdBy ?: "",
                updatedTime = persistModel.updatedTime,
                updatedBy = persistModel.updatedBy ?: ""
            )
        }

        fun toPersistModel(domain: MemberAccount): MemberAccountPersistModel {
            val memberAccountPersistModel = MemberAccountPersistModel(
                id = domain.id,
                role = domain.role,
                username = domain.username,
                deleted = domain.deleted,
                password = domain.password
            )
            memberAccountPersistModel.createdTime = domain.createdTime
            memberAccountPersistModel.createdBy = domain.createdBy
            memberAccountPersistModel.updatedTime = domain.updatedTime
            memberAccountPersistModel.updatedBy = domain.updatedBy
            return memberAccountPersistModel
        }
    }
}
