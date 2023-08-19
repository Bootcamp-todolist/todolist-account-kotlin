package com.todolist.account.adapter.controller

import com.todolist.account.application.AdminAccountApplicationService
import com.todolist.account.application.models.AdminAccountRegisterCommand
import com.todolist.account.application.models.AdminLoginCommand
import com.todolist.account.application.models.PasswordUpdateCommand
import com.todolist.account.application.models.TokenDTO
import com.todolist.account.common.Constant.USER_ID
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class AdminAccountController(private val adminAccountApplicationService: AdminAccountApplicationService) {

    @PostMapping("/login")
    fun adminLogin(@RequestBody adminLoginCommand: AdminLoginCommand): TokenDTO {
        return adminAccountApplicationService.login(adminLoginCommand)
    }

    @PostMapping("/register")
    fun registerAdminAccount(
        @RequestBody registerCommand: AdminAccountRegisterCommand,
        @RequestHeader(USER_ID) userId: String
    ) {
        adminAccountApplicationService.register(registerCommand, userId)
    }

    @PutMapping("/password")
    fun updatePassword(
        @RequestHeader(USER_ID) userId: String,
        @RequestBody passwordUpdateCommand: PasswordUpdateCommand
    ) {
        adminAccountApplicationService.updatePassword(userId, passwordUpdateCommand)
    }
}
