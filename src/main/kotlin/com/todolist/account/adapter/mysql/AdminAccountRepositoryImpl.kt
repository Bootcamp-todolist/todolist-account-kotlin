package com.todolist.account.adapter.mysql

import com.todolist.account.domain.AdminAccount
import com.todolist.account.domain.AdminAccountRepository
import org.springframework.stereotype.Repository

@Repository
class AdminAccountRepositoryImpl(
    private val adminAccountJpaRepository: AdminAccountJpaRepository
) : AdminAccountRepository {
    override fun findByUsername(username: String): AdminAccount? {
        return adminAccountJpaRepository.findByUsername(username).orElse(null)?.toDomain()
    }

}