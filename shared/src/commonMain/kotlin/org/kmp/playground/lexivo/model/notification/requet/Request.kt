package org.kmp.playground.lexivo.model.notification.requet

import org.kmp.playground.lexivo.model.notification.response.NotificationType

data class NotificationRequest(
    val recipientIds: String,
    val title: String,
    val content: String,
    val read: Boolean = false,
    val type: NotificationType
)