package com.todolist.account.application

import com.todolist.account.application.models.AdminLoginCommand
import com.todolist.account.domain.AdminAccount
import com.todolist.account.domain.AdminAccountService
import com.todolist.account.exception.BusinessException
import com.todolist.account.utils.TokenUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class AdminAccountApplicationServiceTest {

    @InjectMocks
    private lateinit var adminAccountApplicationService: AdminAccountApplicationService

    @Mock
    private lateinit var adminAccountService: AdminAccountService

    @Mock
    private lateinit var tokenUtil: TokenUtil

    @Mock
    private lateinit var passwordEncoder: BCryptPasswordEncoder

    @BeforeEach
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testLoginSuccess() {
        val adminAccount = AdminAccount(username = "test", password = "password")

        Mockito.`when`(adminAccountService.findByUsername("test")).thenReturn(adminAccount)
        Mockito.`when`(passwordEncoder.matches("password", adminAccount.password)).thenReturn(true)
        Mockito.`when`(tokenUtil.generateToken(adminAccount.id, adminAccount.role)).thenReturn("mocked-token")

        val loginCommand = AdminLoginCommand(username = "test", password = "password")
        val result = adminAccountApplicationService.login(loginCommand)

        assertEquals("mocked-token", result.token)
    }

    @Test
    fun testLoginUserNotFound() {
        Mockito.`when`(adminAccountService.findByUsername("test")).thenReturn(null)

        val loginCommand = AdminLoginCommand(username = "test-unfound", password = "password")
        assertThrows(BusinessException::class.java) {
            adminAccountApplicationService.login(loginCommand)
        }
    }

    @Test
    fun testLoginIncorrectPassword() {
        val adminAccount = AdminAccount(username = "test", password = "password")
        val loginCommand = AdminLoginCommand(username = "test", password = "wrong-password")

        Mockito.`when`(adminAccountService.findByUsername("test")).thenReturn(adminAccount)
        Mockito.`when`(passwordEncoder.matches("wrong-password", adminAccount.password)).thenReturn(false)

        assertThrows(BusinessException::class.java) {
            adminAccountApplicationService.login(loginCommand)
        }
    }
}
