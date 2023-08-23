package com.todolist.account.adapter.mysql

import com.todolist.account.adapter.mysql.models.MemberAccountPersistModel
import org.springframework.data.jpa.repository.JpaRepository

interface MemberAccountJpaRepository : JpaRepository<MemberAccountPersistModel,String> {
    fun findByDeletedFalse(): List<MemberAccountPersistModel>

}
