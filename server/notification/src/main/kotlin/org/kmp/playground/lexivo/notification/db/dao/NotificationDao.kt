package org.kmp.playground.lexivo.notification.db.dao

import org.bson.types.ObjectId
import org.kmp.playground.lexivo.notification.db.entity.NotificationEntity

interface NotificationDao {
    suspend fun notificationsByRecipient(recipientIds: String): List<NotificationEntity>?
    suspend fun insertNotification(notification: NotificationEntity): NotificationEntity?
    suspend fun getNotificationById(id: ObjectId): NotificationEntity?
    suspend fun updateNotification(notification: NotificationEntity): NotificationEntity?
    suspend fun deleteNotification(id: ObjectId): String?
}