package org.kmp.playground.lexivo.model.review.request

import org.kmp.playground.lexivo.model.review.response.Language
import org.kmp.playground.lexivo.model.review.response.ReviewStatus
import org.kmp.playground.lexivo.model.review.response.ReviewType
import org.kmp.playground.lexivo.model.review.response.Sentiment
import org.kmp.playground.lexivo.model.review.response.Tone

// Represents a request to create a new review
data class ReviewRequest(
    val teamId: String, // Use String for API input
    val userId: String, // Use String for API input
    val originalText: String,
    val correctedText: String,
    val sentiment: Sentiment,
    val tone: Tone,
    val reviewType: List<ReviewType>,
    val language: Language,
    val llmPromptUsed: String,
    val reviewStatus: ReviewStatus,
    val modelVersion: String
)


// Represents a request to filter reviews by Status
data class ReviewsByStatusRequest(
    val status: ReviewStatus
)

// Represents a request to filter reviews by ReviewTypes
data class ReviewsByReviewTypesRequest(
    val reviewTypes: List<ReviewType>
)