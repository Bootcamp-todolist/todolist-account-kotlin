package com.todolist.account.domain

import com.todolist.account.application.models.MemberDTO
import org.springframework.stereotype.Service

@Service
class MemberAccountService(
    private val memberAccountRepository: MemberAccountRepository
) {
    fun getAllMembers(): List<MemberDTO> {
        return memberAccountRepository.findAll()
            .map { memberAccount -> MemberDTO().fromDomain(memberAccount) }
    }

}
