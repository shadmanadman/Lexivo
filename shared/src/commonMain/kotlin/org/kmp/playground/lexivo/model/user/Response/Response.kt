package org.kmp.playground.lexivo.model.user.Response

data class UserResponse(
    val id: String,
    val email: String,
    val name: String,
    val role: UserRole,
    val teamId: String? = null,
    val createdAt: String,
    val updatedAt: String
)

enum class UserRole {
    USER, ADMIN, TEAM_LEAD
}

data class AuthResponse(
    val user: UserResponse,
    val token: String
)