package com.todolist.account.adapter.mysql

import com.todolist.account.adapter.mysql.models.MemberAccountPersistModel
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

    override fun save(memberAccount: MemberAccount): MemberAccount {
        return memberAccountJpaRepository.save(MemberAccountPersistModel.fromDomain(memberAccount))
            .toDomain()
    }

    override fun findByUsername(username: String): MemberAccount? {
        return memberAccountJpaRepository.findByUsername(username)?.toDomain()
    }

    override fun findById(memberId: String): MemberAccount? {
        return memberAccountJpaRepository.findById(memberId).orElse(null)?.toDomain()
    }
}
