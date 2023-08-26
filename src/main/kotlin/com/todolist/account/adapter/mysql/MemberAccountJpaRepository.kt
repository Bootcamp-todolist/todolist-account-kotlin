package com.todolist.account.adapter.mysql

import com.todolist.account.adapter.mysql.models.MemberAccountPersistModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberAccountJpaRepository : JpaRepository<MemberAccountPersistModel,String> {
    fun findByDeletedFalse(): List<MemberAccountPersistModel>
    fun findByUsernameAndDeletedFalse(username: String): Optional<MemberAccountPersistModel>
    fun findByIdAndDeletedFalse(memberId: String): Optional<MemberAccountPersistModel>

}
