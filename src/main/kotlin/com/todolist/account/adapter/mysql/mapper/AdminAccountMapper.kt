package com.todolist.account.adapter.mysql.mapper

import com.todolist.account.adapter.mysql.models.AdminAccountPersistModel
import com.todolist.account.domain.AdminAccount

abstract class AdminAccountMapper {
    companion object {
        fun toDomain(persistModel: AdminAccountPersistModel): AdminAccount {
            return AdminAccount(
                id = persistModel.id,
                role = persistModel.role,
                username = persistModel.username,
                password = persistModel.password,
                createdTime = persistModel.createdTime,
                createdBy = persistModel.createdBy ?: "",
                updatedTime = persistModel.updatedTime,
                updatedBy = persistModel.updatedBy ?: ""
            )
        }

        fun toPersistModel(domain: AdminAccount): AdminAccountPersistModel {
            val adminAccountPersistModel = AdminAccountPersistModel(
                id = domain.id,
                role = domain.role,
                username = domain.username,
                password = domain.password
            )
            adminAccountPersistModel.createdTime = domain.createdTime
            adminAccountPersistModel.createdBy = domain.createdBy
            adminAccountPersistModel.updatedTime = domain.updatedTime
            adminAccountPersistModel.updatedBy = domain.updatedBy
            return adminAccountPersistModel
        }
    }
}
