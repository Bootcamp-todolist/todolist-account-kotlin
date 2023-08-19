package com.todolist.account.adapter.controller

import com.todolist.account.application.AdminAccountApplicationService
import com.todolist.account.application.models.AdminLoginCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminAccountController(private val adminAccountApplicationService: AdminAccountApplicationService) {

    @PostMapping("/login")
    fun adminLogin(@RequestBody adminLoginCommand: AdminLoginCommand): Any {
        return adminAccountApplicationService.login(adminLoginCommand)
    }
}
