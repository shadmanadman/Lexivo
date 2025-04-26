package org.kmp.playground.lexivo.model.feedback.request

import org.kmp.playground.lexivo.model.feedback.response.FeedbackCategory

data class FeedbackRequest(
    val userId: String,
    val reviewId: String,
    val message: String,
    val rating: Int,
    val category: FeedbackCategory
)