package com.todolist.account.application.models

import com.todolist.account.domain.MemberAccount
import java.time.Instant

data class MemberDTO(
    val id: String? = null,
    val username: String? = null,
    val deleted: Boolean = false,
    val createdTime: Instant? = null,
    val createdBy: String? = null
) {
    fun fromDomain(memberAccount: MemberAccount): MemberDTO {
        return MemberDTO(
            id = memberAccount.id,
            username = memberAccount.username,
            deleted = memberAccount.deleted,
            createdTime = memberAccount.createdTime,
            createdBy = memberAccount.createdBy
        )
    }
}
