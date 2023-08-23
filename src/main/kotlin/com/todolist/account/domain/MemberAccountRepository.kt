package com.todolist.account.domain

interface MemberAccountRepository {
    fun findAll(): List<MemberAccount>
    fun save(memberAccount: MemberAccount): MemberAccount
    fun findByUsername(username: String): MemberAccount?
    fun findById(memberId: String): MemberAccount?

}
