package org.kmp.playground.lexivo.feedback.db.dao

import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.client.model.UpdateOptions
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.feedback.db.entity.FeedbackEntity

class FeedbackDaoImpl(val mongoDatabase: MongoDatabase): FeedbackDao {
    companion object {
        const val FEEDBACK_COLLECTION = "feedback"
    }
    override suspend fun feedbackByUserId(userId: ObjectId): List<FeedbackEntity>? =
        mongoDatabase.getCollection<FeedbackEntity>(FEEDBACK_COLLECTION).withDocumentClass<FeedbackEntity>()
            .find(Filters.eq("userId", userId)).toList()

    override suspend fun feedbackByReviewId(reviewId: ObjectId): List<FeedbackEntity>? =
        mongoDatabase.getCollection<FeedbackEntity>(FEEDBACK_COLLECTION).withDocumentClass<FeedbackEntity>()
            .find(Filters.eq("reviewId", reviewId)).toList()

    override suspend fun insertFeedback(feedback: FeedbackEntity): FeedbackEntity? {
        try {
            val result = mongoDatabase.getCollection<FeedbackEntity>(FEEDBACK_COLLECTION).insertOne(
                feedback
            )

            return result.insertedId?.asObjectId()?.value?.let { objectId ->
                mongoDatabase.getCollection<FeedbackEntity>(FEEDBACK_COLLECTION)
                    .find(Filters.eq("_id", objectId))
                    .firstOrNull()
            }
        } catch (e: MongoException) {
            System.err.println("Unable to insert due to an error: $e")
        }

        return null
    }

    override suspend fun getFeedbackById(id: ObjectId): FeedbackEntity? =
        mongoDatabase.getCollection<FeedbackEntity>(FEEDBACK_COLLECTION).withDocumentClass<FeedbackEntity>()
            .find(Filters.eq("_id", id))
            .firstOrNull()

    override suspend fun updateFeedback(feedback: FeedbackEntity): FeedbackEntity? {
        try {
            val query = Filters.eq("_id", feedback.id)
            val updates = Updates.combine(
                Updates.set(FeedbackEntity::userId.name, feedback.userId),
                Updates.set(FeedbackEntity::reviewId.name, feedback.reviewId),
                Updates.set(FeedbackEntity::message.name, feedback.message),
                Updates.set(FeedbackEntity::rating.name, feedback.rating),
                Updates.set(FeedbackEntity::category.name, feedback.category)
            )
            val options = UpdateOptions().upsert(true)

            mongoDatabase.getCollection<FeedbackEntity>(FEEDBACK_COLLECTION)
                .updateOne(query, updates, options)

            return mongoDatabase.getCollection<FeedbackEntity>(FEEDBACK_COLLECTION)
                .find(query)
                .firstOrNull()
        } catch (e: MongoException) {
            System.err.println("Unable to update entity due to an error: $e")
        }
        return null
    }

    override suspend fun deleteFeedback(id: ObjectId): String? {
        try {
            val result = mongoDatabase.getCollection<FeedbackEntity>(FEEDBACK_COLLECTION)
                .deleteOne(Filters.eq("_id", id))
            return result.deletedCount.toString()
        } catch (e: MongoException) {
            System.err.println("Unable to delete due to an error: $e")
        }

        return "-1"
    }
}