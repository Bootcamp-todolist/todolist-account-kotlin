package com.todolist.account.adapter.mysql.mapper

import com.todolist.account.adapter.mysql.models.AdminAccountPersistModel
import com.todolist.account.domain.AdminAccount
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface AdminAccountPersistModelMapper {
    fun toDomain(persistModel: AdminAccountPersistModel): AdminAccount
}
