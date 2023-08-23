package com.todolist.account.application

import com.todolist.account.application.models.CreateMemberCommand
import com.todolist.account.application.models.MemberDTO
import com.todolist.account.domain.MemberAccount
import com.todolist.account.domain.MemberAccountService
import com.todolist.account.exception.BusinessException
import com.todolist.account.exception.ErrorMessage
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberAccountApplicationService(
    private val memberAccountService: MemberAccountService,
    private val passwordEncoder: BCryptPasswordEncoder
) {
    fun getAllMembers(): List<MemberDTO> {
        return memberAccountService.findAll()
            .map { memberAccount -> MemberDTO().fromDomain(memberAccount) }
    }

    fun createMember(createMemberCommand: CreateMemberCommand, userId: String): MemberDTO {
        val username: String = createMemberCommand.username
        val account = memberAccountService.findByUsername(username)
        if (Objects.nonNull(account)) {
            throw BusinessException(ErrorMessage.REPEATED_USER_NAME, HttpStatus.BAD_REQUEST)
        }

        val password = passwordEncoder.encode(createMemberCommand.password)
        val memberAccount =
            MemberAccount(username = username, password = password, createdBy = userId)

        return MemberDTO().fromDomain(memberAccountService.save(memberAccount))
    }

}
