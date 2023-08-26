package com.todolist.account.adapter.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.todolist.account.application.MemberAccountApplicationService
import com.todolist.account.application.models.CreateMemberCommand
import com.todolist.account.application.models.MemberDTO
import com.todolist.account.common.Constant.USER_ID
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(AdminMemberontroller::class)
class AdminMemberControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var memberAccountApplicationService: MemberAccountApplicationService

    @Test
    fun `should create member successfully`() {
        val username = "user"
        val password = "password"
        val command = CreateMemberCommand(
            username = username,
            password = password
        )
        val admin = "admin"
        val memberAccount = MemberDTO()

        Mockito.`when`(memberAccountApplicationService.createMember(command, admin))
            .thenReturn(memberAccount)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/admin/member")
                .header(USER_ID, admin)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(command))
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
        Mockito.verify(memberAccountApplicationService).createMember(
            any(),
            ArgumentMatchers.anyString()
        )
    }

    @Test
    fun `should get all members successfully`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/members"))
            .andExpect(MockMvcResultMatchers.status().isOk)
        Mockito.verify(memberAccountApplicationService).getAllMembers()
    }

    @Test
    fun should_delete_member_successfully() {
        Mockito.doNothing().`when`(memberAccountApplicationService)
            .deleteMember(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/admin/member/1")
                .header(USER_ID, "admin")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
        Mockito.verify(memberAccountApplicationService)
            .deleteMember(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
    }
}
