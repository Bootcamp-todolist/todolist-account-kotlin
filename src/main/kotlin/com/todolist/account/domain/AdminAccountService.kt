package com.todolist.account.domain

import org.springframework.stereotype.Service

@Service
class AdminAccountService(
    private val adminAccountRepository: AdminAccountRepository
) {
    fun findByUsername(username: String): AdminAccount? {
        return adminAccountRepository.findByUsername(username)

    }



}
