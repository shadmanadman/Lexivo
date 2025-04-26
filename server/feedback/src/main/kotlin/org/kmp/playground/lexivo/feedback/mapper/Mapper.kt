package org.kmp.playground.lexivo.feedback.mapper

import org.bson.types.ObjectId
import org.kmp.playground.lexivo.feedback.db.entity.FeedbackEntity
import org.kmp.playground.lexivo.model.feedback.request.FeedbackRequest
import org.kmp.playground.lexivo.model.feedback.response.FeedbackResponse

fun FeedbackRequest.toEntity() = FeedbackEntity(
    userId = userId,
    reviewId = reviewId,
    message = message,
    rating = rating,
    category = category,
)


fun FeedbackEntity.toResponse() = FeedbackResponse(
    id = id.toHexString(),
    userId = userId,
    reviewId = reviewId,
    message = message,
    rating = rating,
    category = category,
)