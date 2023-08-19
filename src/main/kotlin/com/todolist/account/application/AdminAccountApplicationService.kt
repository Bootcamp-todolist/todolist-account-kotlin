package com.todolist.account.application

import com.todolist.account.application.models.AdminAccountRegisterCommand
import com.todolist.account.application.models.AdminLoginCommand
import com.todolist.account.application.models.TokenDTO
import com.todolist.account.domain.AdminAccount
import com.todolist.account.domain.AdminAccountService
import com.todolist.account.exception.BusinessException
import com.todolist.account.exception.ErrorMessage
import com.todolist.account.utils.TokenUtil
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminAccountApplicationService(
    private val adminAccountService: AdminAccountService,
    private val tokenUtil: TokenUtil,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun login(adminLoginCommand: AdminLoginCommand): TokenDTO {
        val username = adminLoginCommand.username
        val adminAccount = adminAccountService.findByUsername(username)
            ?: throw BusinessException(ErrorMessage.USER_NOT_EXIST, HttpStatus.NOT_FOUND)

        verifyPassword(adminLoginCommand, adminAccount)

        return TokenDTO(tokenUtil.generateToken(adminAccount.id, adminAccount.role))
    }

    private fun verifyPassword(adminLoginCommand: AdminLoginCommand, adminAccount: AdminAccount) {
        val isPasswordCorrect =
            passwordEncoder.matches(adminLoginCommand.password, adminAccount.password)
        if (!isPasswordCorrect) {
            throw BusinessException(ErrorMessage.AUTHORIZE_FAILED, HttpStatus.UNAUTHORIZED)
        }
    }

    fun register(registerCommand: AdminAccountRegisterCommand, userId: String) {
        val username: String = registerCommand.username
        adminAccountService.findByUsername(username)
            ?.let { throw BusinessException(ErrorMessage.REPEATED_USER_NAME, HttpStatus.BAD_REQUEST) }

        val password = passwordEncoder.encode(registerCommand.password)
        val adminAccount =
            AdminAccount(username = username, password = password, createdBy = userId)

        adminAccountService.save(adminAccount)
    }
}
