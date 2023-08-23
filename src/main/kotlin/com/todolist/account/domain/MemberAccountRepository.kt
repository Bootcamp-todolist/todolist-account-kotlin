package com.todolist.account.domain

interface MemberAccountRepository {
    fun findAll(): List<MemberAccount>

}
