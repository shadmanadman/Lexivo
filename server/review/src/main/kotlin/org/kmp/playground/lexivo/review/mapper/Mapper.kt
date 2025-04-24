package org.kmp.playground.lexivo.review.mapper

import org.bson.types.ObjectId
import org.kmp.playground.lexivo.model.review.request.ReviewRequest
import org.kmp.playground.lexivo.model.review.response.ReviewResponse
import org.kmp.playground.lexivo.model.review.response.ReviewStatus
import org.kmp.playground.lexivo.review.db.entity.ReviewEntity

fun ReviewRequest.toEntity() = ReviewEntity(
    teamId = ObjectId(teamId),
    userId = ObjectId(userId),
    originalText = originalText,
    correctedText = correctedText,
    sentiment = sentiment,
    tone = tone,
    reviewType = reviewType,
    language = language,
    llmPromptUsed = llmPromptUsed,
    modelVersion = modelVersion,
    status = reviewStatus
)

fun ReviewEntity.toResponse() = ReviewResponse(
    id = this.id.toHexString(),
    teamId = this.teamId.toHexString(),
    userId = this.userId.toHexString(),
    originalText = this.originalText,
    correctedText = this.correctedText,
    sentiment = this.sentiment,
    tone = this.tone,
    reviewType = this.reviewType,
    language = this.language,
    llmPromptUsed = this.llmPromptUsed,
    modelVersion = this.modelVersion,
    status = this.status,
    createdAt = this.createdAt.toString(),
    updatedAt = this.updatedAt.toString()
)