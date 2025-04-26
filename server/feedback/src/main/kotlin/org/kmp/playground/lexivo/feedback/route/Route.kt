package org.kmp.playground.lexivo.feedback.route

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.feedback.di.Module
import org.kmp.playground.lexivo.feedback.mapper.toEntity
import org.kmp.playground.lexivo.feedback.mapper.toResponse
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.dateScalar
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.longScalar
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.objectIdScalar
import org.kmp.playground.lexivo.model.feedback.request.FeedbackRequest
import org.kmp.playground.lexivo.model.feedback.response.FeedbackResponse

val FeedbackRepository = Module.feedbackDao()

val SUPPORTED_PACKAGES = listOf(
    "org.kmp.playground.lexivo.feedback",
    "org.kmp.playground.lexivo.feedback.route",
    "org.kmp.playground.lexivo.feedback.mapper",
    "org.kmp.playground.lexivo",
    "kotlin"
)

class FeedbackQuery : Query {

    suspend fun feedbackById(id: String): FeedbackResponse? {
        return FeedbackRepository
            .getFeedbackById(ObjectId(id))
            ?.toResponse()
            ?: throw Exception("This id $id not found")
    }

    suspend fun feedbacksByUserId(userId: String): List<FeedbackResponse>? {
        return FeedbackRepository
            .feedbackByUserId(userId = ObjectId(userId))
            ?.map { it.toResponse() }
            ?: throw Exception("This id $userId not found")
    }

    suspend fun feedbacksByReviewId(reviewId: String): List<FeedbackResponse>? {
        return FeedbackRepository
            .feedbackByReviewId(reviewId = ObjectId(reviewId))
            ?.map { it.toResponse() }
            ?: throw Exception("This id $reviewId not found")
    }
}

class FeedbackMutations : Mutation {
    suspend fun deleteFeedback(id: String): String? {
        return FeedbackRepository.deleteFeedback(ObjectId(id))
    }

    suspend fun updateFeedback(feedback: FeedbackRequest): FeedbackResponse? =
        FeedbackRepository.updateFeedback(feedback.toEntity())?.toResponse()


    suspend fun insertFeedback(feedback: FeedbackRequest): FeedbackResponse? =
        FeedbackRepository.insertFeedback(feedback.toEntity())?.toResponse()
}



val feedbackSchema = toSchema(
    config = SchemaGeneratorConfig(
        supportedPackages = SUPPORTED_PACKAGES,
        additionalTypes = setOf(objectIdScalar, dateScalar, longScalar)

    ),
    queries = listOf(TopLevelObject(FeedbackQuery())),
    mutations = listOf(TopLevelObject(FeedbackMutations()))
)