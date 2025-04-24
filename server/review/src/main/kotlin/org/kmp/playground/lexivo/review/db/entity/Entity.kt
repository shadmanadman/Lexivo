package org.kmp.playground.lexivo.review.db.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.model.review.response.Language
import org.kmp.playground.lexivo.model.review.response.ReviewStatus
import org.kmp.playground.lexivo.model.review.response.ReviewType
import org.kmp.playground.lexivo.model.review.response.Sentiment
import org.kmp.playground.lexivo.model.review.response.Tone
import java.time.LocalDateTime

data class ReviewEntity(
    @BsonId
    val id: ObjectId = ObjectId(),
    val teamId: ObjectId,
    val userId: ObjectId,
    val originalText: String,
    val correctedText: String,
    val sentiment: Sentiment,
    val tone: Tone,
    val reviewType: List<ReviewType>,
    val language: Language,
    val llmPromptUsed: String,
    val modelVersion: String,
    val status: ReviewStatus,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)