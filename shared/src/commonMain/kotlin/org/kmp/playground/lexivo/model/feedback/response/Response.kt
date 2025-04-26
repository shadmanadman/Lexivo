package org.kmp.playground.lexivo.model.feedback.response

enum class FeedbackCategory {
    QUALITY, BUG, SUGGESTION, OTHER
}

data class FeedbackResponse(
    val id: String,
    val userId: String,
    val reviewId: String,
    val message: String,
    val rating: Int,
    val category: FeedbackCategory
)