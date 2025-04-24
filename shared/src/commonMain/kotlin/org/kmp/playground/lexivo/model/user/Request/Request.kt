package org.kmp.playground.lexivo.model.user.Request

import org.kmp.playground.lexivo.model.user.Response.UserRole

data class UserRequest(
    val email: String,
    val name: String,
    val provider: AuthProvider,
    val avatar: String? = null,
    val providerId: String,
    val role: UserRole = UserRole.USER,
    val hashedPassword: String? = null,
    val teamId: String? = null,
    val createdAt: String,
    val updatedAt: String
)

data class RegisterRequest(
    val email: String,
    val name: String,
    val password: String
)

data class OAuthRegisterRequest(
    val email: String,
    val name: String,
    val provider: AuthProvider,
    val providerId: String
)


enum class AuthProvider {
    GOOGLE, GITHUB
}

data class LoginRequest(
    val email: String,
    val password: String
)

