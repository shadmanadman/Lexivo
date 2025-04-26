package org.kmp.playground.lexivo.notification.route

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.dateScalar
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.longScalar
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.objectIdScalar
import org.kmp.playground.lexivo.model.notification.requet.NotificationRequest
import org.kmp.playground.lexivo.model.notification.response.NotificationResponse
import org.kmp.playground.lexivo.notification.di.Module
import org.kmp.playground.lexivo.notification.mapper.toEntity
import org.kmp.playground.lexivo.notification.mapper.toResponse

val NotificationRepository = Module.notificationDao()

val SUPPORTED_PACKAGES = listOf(
    "org.kmp.playground.lexivo.notification",
    "org.kmp.playground.lexivo.notification.route",
    "org.kmp.playground.lexivo.notification.mapper",
    "org.kmp.playground.lexivo",
    "kotlin"
)

class NotificationQuery : Query {

    suspend fun notificationById(id: String): NotificationResponse? {
        return NotificationRepository
            .getNotificationById(ObjectId(id))
            ?.toResponse()
            ?: throw Exception("This id $id not found")
    }

    suspend fun notificationsByRecipientId(recipientId: String): List<NotificationResponse>? {
        return NotificationRepository
            .notificationsByRecipient(recipientId)
            ?.map { it.toResponse() }
            ?: throw Exception("This id $recipientId not found")
    }
}

class NotificationMutations : Mutation {
    suspend fun deleteNotification(id: String): String? {
        return NotificationRepository.deleteNotification(ObjectId(id))
    }

    suspend fun updateReview(notification: NotificationRequest): NotificationResponse? =
        NotificationRepository.updateNotification(notification.toEntity())?.toResponse()


    suspend fun insertNotification(notification: NotificationRequest): NotificationResponse? =
        NotificationRepository.insertNotification(notification.toEntity())?.toResponse()
}



val notificationSchema = toSchema(
    config = SchemaGeneratorConfig(
        supportedPackages = SUPPORTED_PACKAGES,
        additionalTypes = setOf(objectIdScalar, dateScalar, longScalar)

    ),
    queries = listOf(TopLevelObject(NotificationQuery())),
    mutations = listOf(TopLevelObject(NotificationMutations()))
)