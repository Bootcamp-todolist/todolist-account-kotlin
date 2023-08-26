package com.todolist.account.adapter.mysql

import com.todolist.account.adapter.mysql.mapper.AdminAccountMapper
import com.todolist.account.domain.AdminAccount
import com.todolist.account.domain.AdminAccountRepository
import org.springframework.stereotype.Repository

@Repository
class AdminAccountRepositoryImpl(
    private val adminAccountJpaRepository: AdminAccountJpaRepository
) : AdminAccountRepository {
    override fun findByUsername(username: String): AdminAccount? {
        return adminAccountJpaRepository.findByUsername(username)
            .map { AdminAccountMapper.toDomain(it) }
            .orElse(null)
    }

    override fun save(adminAccount: AdminAccount) {
        adminAccountJpaRepository.save(AdminAccountMapper.toPersistModel(adminAccount))
    }

    override fun findById(userId: String): AdminAccount? {
        return adminAccountJpaRepository.findById(userId)
            .map { AdminAccountMapper.toDomain(it) }
            .orElse(null)
    }

}
