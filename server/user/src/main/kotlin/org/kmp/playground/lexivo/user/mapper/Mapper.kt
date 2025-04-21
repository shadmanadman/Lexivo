package org.kmp.playground.lexivo.user.mapper

import org.kmp.playground.lexivo.model.user.Response.UserResponse
import org.kmp.playground.lexivo.user.db.entity.UserEntity

fun UserEntity.toResponse(): UserResponse = UserResponse(
    id = this.id.toHexString(),
    email = this.email,
    name = this.name,
    role = this.role,
    teamId = this.teamId?.toHexString(),
    createdAt = this.createdAt.toString(),
    updatedAt = this.updatedAt.toString()
)