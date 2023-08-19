package com.todolist.account.domain

interface AdminAccountRepository {
    fun findByUsername(username: String): AdminAccount?
}
