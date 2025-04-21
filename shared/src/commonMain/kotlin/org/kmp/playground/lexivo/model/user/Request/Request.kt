package org.kmp.playground.lexivo.model.user.Request

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

