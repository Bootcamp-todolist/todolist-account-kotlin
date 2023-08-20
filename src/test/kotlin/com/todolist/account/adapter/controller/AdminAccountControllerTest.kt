package com.todolist.account.adapter.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.todolist.account.application.AdminAccountApplicationService
import com.todolist.account.application.models.AdminLoginCommand
import com.todolist.account.application.models.TokenDTO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(AdminAccountController::class)
class AdminAccountControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var adminAccountApplicationService: AdminAccountApplicationService

    @Test
    fun testAdminLogin() {
        val adminLoginCommand = AdminLoginCommand("username", "password")
        val tokenDto = TokenDTO("token")

        `when`(adminAccountApplicationService.login(adminLoginCommand)).thenReturn(tokenDto)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(adminLoginCommand))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("token"))
        Mockito.verify(adminAccountApplicationService).login(adminLoginCommand)
    }
}
