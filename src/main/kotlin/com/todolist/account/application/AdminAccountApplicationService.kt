package com.todolist.account.application

import com.todolist.account.application.models.AdminAccountRegisterCommand
import com.todolist.account.application.models.AdminLoginCommand
import com.todolist.account.application.models.PasswordUpdateCommand
import com.todolist.account.application.models.TokenDTO
import com.todolist.account.domain.AdminAccount
import com.todolist.account.domain.AdminAccountService
import com.todolist.account.exception.BusinessException
import com.todolist.account.exception.ErrorMessage
import com.todolist.account.utils.TokenUtil
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.Objects

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
        val account = adminAccountService.findByUsername(username)
        if (Objects.nonNull(account)) {
            throw BusinessException(ErrorMessage.REPEATED_USER_NAME, HttpStatus.BAD_REQUEST)
        }

        val password = passwordEncoder.encode(registerCommand.password)
        val adminAccount = AdminAccount(
            username = "test",
            password = password,
            createdTime = Instant.now(),
            createdBy = "test",
            updatedTime = Instant.now(),
            updatedBy = "test"
        )

        adminAccountService.save(adminAccount)
    }

    fun updatePassword(userId: String, passwordUpdateCommand: PasswordUpdateCommand) {
        val account = adminAccountService.findById(userId)
            ?: throw BusinessException(ErrorMessage.USER_NOT_EXIST, HttpStatus.NOT_FOUND)
        if (!passwordEncoder.matches(passwordUpdateCommand.oldPassword, account.password)) {
            throw BusinessException(ErrorMessage.OLD_PASSWORD_WRONG, HttpStatus.BAD_REQUEST)
        }

        adminAccountService.save(
            account.copy(
                password = passwordEncoder.encode(
                    passwordUpdateCommand.newPassword),
                createdBy = userId
            )
        )
    }
}
