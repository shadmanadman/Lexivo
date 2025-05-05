package org.kmp.playground.lexivo.notification.db.dao

import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.notification.db.entity.NotificationEntity
import java.time.LocalDateTime

class NotificationDaoImpl(val mongoDatabase: MongoDatabase): NotificationDao {

    companion object {
        const val NOTIFICATION_COLLECTION = "notification"
    }
    override suspend fun notificationsByRecipient(recipientIds: String): List<NotificationEntity>? =
        mongoDatabase.getCollection<NotificationEntity>(NOTIFICATION_COLLECTION).withDocumentClass<NotificationEntity>()
            .find(Filters.eq("recipientIds", recipientIds)).toList()


    override suspend fun insertNotification(notification: NotificationEntity): NotificationEntity? {
        try {
            val result = mongoDatabase.getCollection<NotificationEntity>(NOTIFICATION_COLLECTION).insertOne(
                notification
            )

            return result.insertedId?.asObjectId()?.value?.let { objectId ->
                mongoDatabase.getCollection<NotificationEntity>(NOTIFICATION_COLLECTION)
                    .find(Filters.eq("_id", objectId))
                    .firstOrNull()
            }
        } catch (e: MongoException) {
            System.err.println("Unable to insert due to an error: $e")
        }

        return null
    }

    override suspend fun getNotificationById(id: ObjectId): NotificationEntity? =
        mongoDatabase.getCollection<NotificationEntity>(NOTIFICATION_COLLECTION).withDocumentClass<NotificationEntity>()
            .find(Filters.eq("_id", id))
            .firstOrNull()

    override suspend fun updateNotification(notification: NotificationEntity): NotificationEntity? {
        try {
            val query = Filters.eq("_id", notification.id)
            val updates = Updates.combine(
                Updates.set(NotificationEntity::title.name, notification.title),
                Updates.set(NotificationEntity::content.name, notification.content),
                Updates.set(NotificationEntity::recipientIds.name, notification.recipientIds),
                Updates.set(NotificationEntity::read.name, notification.read),
                Updates.set(NotificationEntity::type.name, notification.type),
                Updates.set(NotificationEntity::createdAt.name, LocalDateTime.now())
            )
            val options = UpdateOptions().upsert(true)

            mongoDatabase.getCollection<NotificationEntity>(NOTIFICATION_COLLECTION)
                .updateOne(query, updates, options)

            return mongoDatabase.getCollection<NotificationEntity>(NOTIFICATION_COLLECTION)
                .find(query)
                .firstOrNull()
        } catch (e: MongoException) {
            System.err.println("Unable to update entity due to an error: $e")
        }
        return null
    }

    override suspend fun deleteNotification(id: ObjectId): String? {
        try {
            val result = mongoDatabase.getCollection<NotificationEntity>(NOTIFICATION_COLLECTION)
                .deleteOne(Filters.eq("_id", id))
            return result.deletedCount.toString()
        } catch (e: MongoException) {
            System.err.println("Unable to delete due to an error: $e")
        }

        return "-1"
    }
}