package org.kmp.playground.lexivo.team.mapper

import org.bson.types.ObjectId
import org.kmp.playground.lexivo.model.team.request.TeamRequest
import org.kmp.playground.lexivo.model.team.response.TeamResponse
import org.kmp.playground.lexivo.team.db.entity.TeamEntity

fun TeamRequest.toEntity() = TeamEntity(
    id = ObjectId().toHexString(),
    name = name,
    ownerId = ObjectId(ownerId),
    members = members.map { ObjectId(it) },
    styleGuide = styleGuide
)


fun TeamEntity.toResponse() = TeamResponse(
    id = id,
    name = name,
    ownerId = ownerId.toHexString(),
    members = members.map { it.toHexString() },
    styleGuide = styleGuide
)