package com.todolist.account.domain

interface AdminAccountRepository {
    fun findByUsername(username: String): AdminAccount?
    fun save(adminAccount: AdminAccount)
    fun findById(userId: String): AdminAccount?
}
