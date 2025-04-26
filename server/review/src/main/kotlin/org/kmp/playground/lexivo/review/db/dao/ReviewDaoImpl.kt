package org.kmp.playground.lexivo.review.db.dao

import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.model.review.response.ReviewStatus
import org.kmp.playground.lexivo.model.review.response.ReviewType
import org.kmp.playground.lexivo.review.db.entity.ReviewEntity
import java.time.LocalDateTime

class ReviewDaoImpl(val mongoDatabase: MongoDatabase) : ReviewDao {
    companion object {
        const val REVIEW_COLLECTION = "review"
    }

    override suspend fun insertReview(review: ReviewEntity): ReviewEntity? {
        try {
            val result = mongoDatabase.getCollection<ReviewEntity>(REVIEW_COLLECTION).insertOne(
                review
            )

            return result.insertedId?.asObjectId()?.value?.let { objectId ->
                mongoDatabase.getCollection<ReviewEntity>(REVIEW_COLLECTION)
                    .find(Filters.eq("_id", objectId))
                    .firstOrNull()
            }
        } catch (e: MongoException) {
            System.err.println("Unable to insert due to an error: $e")
        }

        return null
    }

    override suspend fun reviews(): List<ReviewEntity> =
        mongoDatabase.getCollection<ReviewEntity>(REVIEW_COLLECTION).find().toList()

    override suspend fun getReviewById(id: ObjectId): ReviewEntity? =
        mongoDatabase.getCollection<ReviewEntity>(REVIEW_COLLECTION)
            .withDocumentClass<ReviewEntity>()
            .find(Filters.eq("_id", id))
            .firstOrNull()

    override suspend fun getReviewsByTeamId(teamId: ObjectId): List<ReviewEntity>? =
        mongoDatabase.getCollection<ReviewEntity>(REVIEW_COLLECTION)
            .withDocumentClass<ReviewEntity>()
            .find(Filters.eq("_teamId", teamId))
            .toList()

    override suspend fun getReviewsByUserId(userId: ObjectId): List<ReviewEntity>? =
        mongoDatabase.getCollection<ReviewEntity>(REVIEW_COLLECTION)
            .withDocumentClass<ReviewEntity>()
            .find(Filters.eq("_userId", userId))
            .toList()

    override suspend fun updateReview(review: ReviewEntity): ReviewEntity? {
        try {
            val query = Filters.eq("_id", review.id)
            val updates = Updates.combine(
                Updates.set("originalText", review.originalText),
                Updates.set("correctedText", review.correctedText),
                Updates.set("sentiment", review.sentiment),
                Updates.set("tone", review.tone),
                Updates.set("reviewType", review.reviewType),
                Updates.set("language", review.language),
                Updates.set("llmPromptUsed", review.llmPromptUsed),
                Updates.set("modelVersion", review.modelVersion),
                Updates.set("status", review.status),
                Updates.set("updatedAt", LocalDateTime.now())
            )
            val options = UpdateOptions().upsert(true)

            mongoDatabase.getCollection<ReviewEntity>(REVIEW_COLLECTION)
                .updateOne(query, updates, options)

            return mongoDatabase.getCollection<ReviewEntity>(REVIEW_COLLECTION)
                .find(query)
                .firstOrNull()
        } catch (e: MongoException) {
            System.err.println("Unable to update entity due to an error: $e")
        }
        return null
    }

    override suspend fun deleteReviewById(id: ObjectId): String {
        try {
            val result = mongoDatabase.getCollection<ReviewEntity>(REVIEW_COLLECTION)
                .deleteOne(Filters.eq("_id", id))
            return result.deletedCount.toString()
        } catch (e: MongoException) {
            System.err.println("Unable to delete due to an error: $e")
        }

        return "-1"
    }

    override suspend fun getReviewsByStatus(status: ReviewStatus): ReviewEntity? =
        mongoDatabase.getCollection<ReviewEntity>(REVIEW_COLLECTION)
            .withDocumentClass<ReviewEntity>()
            .find(Filters.eq("status", status))
            .firstOrNull()

    override suspend fun getReviewsByReviewTypes(reviewTypes: List<ReviewType>): ReviewEntity? =
        mongoDatabase.getCollection<ReviewEntity>(REVIEW_COLLECTION)
            .withDocumentClass<ReviewEntity>()
            .find(Filters.`in`("reviewType", reviewTypes.map { it }))
            .firstOrNull()
    
}