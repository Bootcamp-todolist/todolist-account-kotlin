package com.todolist.account.adapter.controller

import com.todolist.account.application.MemberAccountApplicationService
import com.todolist.account.application.models.CreateMemberCommand
import com.todolist.account.application.models.MemberDTO
import com.todolist.account.common.Constant.USER_ID
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class AccountMemberController(
    private val memberAccountApplicationService: MemberAccountApplicationService
) {
    @GetMapping("/members")
    fun getAllMembers(): List<MemberDTO> {
        return memberAccountApplicationService.getAllMembers()
    }

    @PostMapping("/member")
    @ResponseStatus(HttpStatus.CREATED)
    fun createMember(
        @RequestBody createMemberCommand: CreateMemberCommand,
        @RequestHeader(USER_ID) userId: String
    ): MemberDTO {
        return memberAccountApplicationService.createMember(createMemberCommand, userId)
    }

    @DeleteMapping("/member/{id}")
    fun deleteMember(
        @PathVariable("id") memberId: String,
        @RequestHeader(USER_ID) userId: String
    ) {
        memberAccountApplicationService.deleteMember(memberId, userId)
    }
}
