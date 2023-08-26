package com.todolist.account.application

import com.todolist.account.application.models.CreateMemberCommand
import com.todolist.account.application.models.MemberDTO
import com.todolist.account.domain.MemberAccount
import com.todolist.account.domain.MemberAccountService
import com.todolist.account.exception.BusinessException
import com.todolist.account.exception.ErrorMessage
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.Instant

class MemberAccountApplicationServiceTest {
    @InjectMocks
    private lateinit var memberAccountApplicationService: MemberAccountApplicationService

    @Mock
    private lateinit var memberAccountService: MemberAccountService

    @Mock
    private lateinit var passwordEncoder: BCryptPasswordEncoder

    @BeforeEach
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    private fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()


    @Test
    fun `should create member successfully`() {
        val username = "user"
        val admin = "admin"
        val password = "encode-password"
        val command = CreateMemberCommand(
            username = username,
            password = "password")
        val memberAccount = MemberAccount(
            id = "id",
            username = "username",
            password = "password",
            deleted = false,
            createdBy = admin,
            createdTime = Instant.now(),
            updatedBy = "test",
            updatedTime = Instant.now()
        )

        `when`(memberAccountService.save(any())).thenReturn(memberAccount)
        `when`(passwordEncoder.encode(any())).thenReturn(password)

        val member: MemberDTO = memberAccountApplicationService.createMember(command, admin)
        assertThat(member).isNotNull
        Mockito.verify(passwordEncoder).encode(any())
        Mockito.verify(memberAccountService).save(any())
    }

    @Test
    fun `should get all members`() {
        val username = "user"
        val admin = "admin"
        val password = "encode-password"
        val memberAccounts = List(2) {
            MemberAccount(
                id = "id",
                username = username,
                password = password,
                deleted = false,
                createdBy = admin,
                createdTime = Instant.now(),
                updatedBy = "test",
                updatedTime = Instant.now()
            )
        }

        Mockito.doReturn(memberAccounts).`when`(memberAccountService).findAll()
        val allMembers: List<MemberDTO> = memberAccountApplicationService.getAllMembers()
        assertThat(allMembers).usingRecursiveComparison()
            .isEqualTo(memberAccounts)
        Mockito.verify(memberAccountService).findAll()
    }

    @Test
    fun `should deleted member successfully`() {
        val captor: ArgumentCaptor<MemberAccount> = ArgumentCaptor.forClass(MemberAccount::class.java)
        val id = "123"
        val admin = "admin"
        val memberAccount = MemberAccount(
            id = "id",
            username = "username",
            password = "password",
            deleted = false,
            createdBy = admin,
            createdTime = Instant.now(),
            updatedBy = "test",
            updatedTime = Instant.now()
        )

        doReturn(memberAccount).`when`(memberAccountService).findById(id)

        memberAccountApplicationService.deleteMember(id, admin)
        Mockito.verify(memberAccountService).findById(any())
        Mockito.verify(memberAccountService).save(capture(captor))
        assertThat(captor.value.deleted).isTrue
        assertThat(captor.value.updatedBy).isEqualTo(admin)
    }

    @Test
    fun `should return 404 when can not found member`() {
        val id = "123"
        Mockito.doReturn(null).`when`(memberAccountService).findById(id)
        val exception = Assertions.catchThrowable {
            memberAccountApplicationService.deleteMember(
                id,
                "admin"
            )
        }
        val businessException: BusinessException = exception as BusinessException
        assertThat(businessException.httpStatus).isEqualTo(HttpStatus.NOT_FOUND)
        assertThat(businessException.errorMessage).isEqualTo(ErrorMessage.USER_NOT_EXIST)
    }
}
