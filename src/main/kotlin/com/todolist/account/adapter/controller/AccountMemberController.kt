package com.todolist.account.adapter.controller

import com.todolist.account.application.models.MemberDTO
import com.todolist.account.domain.MemberAccountService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AccountMemberController(
    private val memberAccountService: MemberAccountService
) {
    @GetMapping("/members")
    fun getAllMembers(): List<MemberDTO> {
        return memberAccountService.getAllMembers()
    }
}
