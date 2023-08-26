package com.todolist.account.adapter.mysql

import com.todolist.account.adapter.mysql.mapper.MemberAccountMapper
import com.todolist.account.domain.MemberAccount
import com.todolist.account.domain.MemberAccountRepository
import org.springframework.stereotype.Repository

@Repository
class MemberAccountRepositoryImpl(
    private val memberAccountJpaRepository: MemberAccountJpaRepository
) : MemberAccountRepository {
    override fun findAll(): List<MemberAccount> {
        return memberAccountJpaRepository.findByDeletedFalse()
            .map { memberAccountPersistModel ->
                MemberAccountMapper.toDomain(memberAccountPersistModel)
            }
    }

    override fun save(memberAccount: MemberAccount): MemberAccount {
        return MemberAccountMapper.toDomain(
            memberAccountJpaRepository.save(MemberAccountMapper.toPersistModel(memberAccount))
        )
    }

    override fun findByUsername(username: String): MemberAccount? {
        return memberAccountJpaRepository.findByUsernameAndDeletedFalse(username)
            .map { MemberAccountMapper.toDomain(it) }
            .orElse(null)
    }

    override fun findById(memberId: String): MemberAccount? {
        return memberAccountJpaRepository.findByIdAndDeletedFalse(memberId)
            .map { MemberAccountMapper.toDomain(it) }.orElse(null)
    }
}
