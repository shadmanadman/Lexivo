package org.kmp.playground.lexivo.model.notification.response

enum class NotificationType {
    REVIEW_SHARED, FEEDBACK_RECEIVED, SYSTEM
}




data class NotificationResponse(
    val id: String,
    val recipientIds: String,
    val title: String,
    val content: String,
    val read: Boolean = false,
    val type: NotificationType,
    val createdAt: String
)