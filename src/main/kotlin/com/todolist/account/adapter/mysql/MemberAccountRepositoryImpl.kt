package com.todolist.account.adapter.mysql

import com.todolist.account.domain.MemberAccount
import com.todolist.account.domain.MemberAccountRepository
import org.springframework.stereotype.Repository

@Repository
class MemberAccountRepositoryImpl(
    private val memberAccountJpaRepository: MemberAccountJpaRepository
) : MemberAccountRepository {
    override fun findAll(): List<MemberAccount> {
        return memberAccountJpaRepository.findByDeletedFalse()
            .map { memberAccountPersistModel -> memberAccountPersistModel.toDomain() }
    }
}
