package org.kmp.playground.lexivo.team.db.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.model.team.response.StyleGuide

data class TeamEntity(
    @BsonId
    val id: String,
    val name: String,
    val ownerId: ObjectId,
    val members: List<ObjectId>,
    val styleGuide: StyleGuide,
)