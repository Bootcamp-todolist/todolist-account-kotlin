package com.todolist.account.domain

import org.springframework.stereotype.Service

@Service
class MemberAccountService(
    private val memberAccountRepository: MemberAccountRepository
) {
    fun findAll(): List<MemberAccount> {
        return memberAccountRepository.findAll()
    }

    fun save(memberAccount: MemberAccount): MemberAccount {
        return memberAccountRepository.save(memberAccount)
    }

    fun findByUsername(username: String): MemberAccount? {
        return memberAccountRepository.findByUsername(username)
    }

    fun findById(memberId: String): MemberAccount? {
        return memberAccountRepository.findById(memberId)
    }


}
