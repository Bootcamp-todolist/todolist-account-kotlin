package com.todolist.account.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.todolist.account.domain.enum.Role
import com.todolist.account.exception.BusinessException
import com.todolist.account.exception.ErrorMessage
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class TokenUtil {
    private val log = KotlinLogging.logger {}

    fun generateToken(userId: String, userRole: Role): String {
        val secret = System.getenv("JWT_SECRET")
        validSecretKey(secret)
        val algorithm = Algorithm.HMAC512(secret)
        return JWT.create()
            .withIssuer("todo-list")
            .withSubject(userId)
            .withClaim("role", userRole.toString())
            .withExpiresAt(Instant.now().plus(12, ChronoUnit.HOURS))
            .sign(algorithm)
    }

    private fun validSecretKey(secret: String?) {
        if (secret.isNullOrBlank()) {
            log.error("Please set JWT_SECRET")
            throw BusinessException(ErrorMessage.AUTHORIZE_FAILED)
        }
    }
}
