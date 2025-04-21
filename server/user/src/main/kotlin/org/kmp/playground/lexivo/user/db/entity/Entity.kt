package org.kmp.playground.lexivo.user.db.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.model.user.Request.AuthProvider
import org.kmp.playground.lexivo.model.user.Response.UserResponse
import org.kmp.playground.lexivo.model.user.Response.UserRole
import java.util.Date

data class UserEntity(
    @BsonId val id: ObjectId = ObjectId(),
    val email: String,
    val name: String,
    val provider: AuthProvider,
    val avatar: String? = null,
    val providerId: String,
    val role: UserRole = UserRole.USER,
    val hashedPassword: String? = null,
    val teamId: ObjectId? = null,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
