package org.kmp.playground.lexivo.notification.mapper

import org.bson.types.ObjectId
import org.kmp.playground.lexivo.model.notification.requet.NotificationRequest
import org.kmp.playground.lexivo.model.notification.response.NotificationResponse
import org.kmp.playground.lexivo.notification.db.entity.NotificationEntity

fun NotificationRequest.toEntity() = NotificationEntity(
    recipientIds = this.recipientIds,
    title = this.title,
    content = this.content,
    type = this.type,
    read = this.read,
)

fun NotificationEntity.toResponse() = NotificationResponse(
    id = this.id.toString(),
    recipientIds = this.recipientIds,
    title = this.title,
    content = this.content,
    type = this.type,
    createdAt = this.createdAt.toString()
)