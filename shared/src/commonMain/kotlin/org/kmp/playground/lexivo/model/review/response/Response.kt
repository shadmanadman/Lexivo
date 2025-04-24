package org.kmp.playground.lexivo.model.review.response


data class ReviewResponse(
    val id: String,
    val teamId: String,
    val userId: String,
    val originalText: String,
    val correctedText: String,
    val sentiment: Sentiment,
    val tone: Tone,
    val reviewType: List<ReviewType>,
    val language: Language,
    val llmPromptUsed: String,
    val modelVersion: String,
    val status: ReviewStatus,
    val createdAt: String,
    val updatedAt: String
)

enum class Sentiment {
    POSITIVE,
    NEUTRAL,
    NEGATIVE
}

enum class Tone {
    PROFESSIONAL,
    FRIENDLY,
    CASUAL,
    ANGRY,
    NEUTRAL
}

enum class ReviewType {
    GRAMMAR,
    TONE,
    SENTIMENT,
    TRANSLATION
}

enum class Language(val code: String) {
    ENGLISH("en"),
    SPANISH("es"),
    FRENCH("fr");

    companion object {
        fun fromCode(code: String): Language? {
            return entries.find { it.code == code }
        }
    }
}

enum class ReviewStatus {
    DRAFT,
    FINAL
}