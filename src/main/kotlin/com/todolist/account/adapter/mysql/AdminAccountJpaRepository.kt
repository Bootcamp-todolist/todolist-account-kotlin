package com.todolist.account.adapter.mysql

import com.todolist.account.adapter.mysql.models.AdminAccountPersistModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface AdminAccountJpaRepository: JpaRepository<AdminAccountPersistModel,String> {
    fun findByUsername(username: String): Optional<AdminAccountPersistModel>

}
