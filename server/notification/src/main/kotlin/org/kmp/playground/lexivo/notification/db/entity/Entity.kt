package org.kmp.playground.lexivo.notification.db.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.model.notification.response.NotificationType
import java.time.LocalDateTime

data class NotificationEntity(
    @BsonId
    val id: ObjectId = ObjectId(),
    val recipientIds: String,
    val title: String,
    val content: String,
    val read: Boolean = false,
    val type: NotificationType,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
