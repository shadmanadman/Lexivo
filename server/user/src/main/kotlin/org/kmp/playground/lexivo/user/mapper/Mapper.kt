package org.kmp.playground.lexivo.user.mapper

import org.bson.types.ObjectId
import org.kmp.playground.lexivo.model.user.Request.UserRequest
import org.kmp.playground.lexivo.model.user.Response.UserResponse
import org.kmp.playground.lexivo.user.db.entity.UserEntity
import java.util.Date

fun UserEntity.toResponse(): UserResponse = UserResponse(
    id = this.id.toHexString(),
    email = this.email,
    name = this.name,
    role = this.role,
    teamId = this.teamId?.toHexString(),
    createdAt = this.createdAt.toString(),
    updatedAt = this.updatedAt.toString()
)

fun UserRequest.toEntity(): UserEntity =
    UserEntity(
        email = this.email,
        name = this.name,
        role = this.role,
        teamId = ObjectId(this.teamId),
        avatar = this.avatar,
        createdAt = Date(),
        updatedAt = Date()
    )