package org.kmp.playground.lexivo.review.route

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.dateScalar
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.longScalar
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.objectIdScalar
import org.kmp.playground.lexivo.model.review.request.ReviewRequest
import org.kmp.playground.lexivo.model.review.response.ReviewResponse
import org.kmp.playground.lexivo.model.review.response.ReviewStatus
import org.kmp.playground.lexivo.model.review.response.ReviewType
import org.kmp.playground.lexivo.review.di.Module
import org.kmp.playground.lexivo.review.mapper.toEntity
import org.kmp.playground.lexivo.review.mapper.toResponse

val reviewRepository = Module.reviewDao()

val SUPPORTED_PACKAGES = listOf(
    "org.kmp.playground.lexivo.review",
    "org.kmp.playground.lexivo.review.route",
    "org.kmp.playground.lexivo.review.mapper",
    "org.kmp.playground.lexivo",
    "kotlin"
)

class ReviewQuery : Query {

    suspend fun reviewById(id: String): ReviewResponse? {
        return reviewRepository
            .getReviewById(ObjectId(id))
            ?.toResponse()
            ?: throw Exception("This id $id not found")
    }

    suspend fun reviewByTeamId(id: String): ReviewResponse? {
        return reviewRepository
            .getReviewsByTeamId(ObjectId(id))
            ?.toResponse()
            ?: throw Exception("This id $id not found")
    }

    suspend fun reviewByStatus(status: ReviewStatus): ReviewResponse? {
        return reviewRepository
            .getReviewsByStatus(status)
            ?.toResponse()
            ?: throw Exception("This review status $status not found")
    }

    suspend fun reviewByType(type: List<ReviewType>): ReviewResponse? {
        return reviewRepository
            .getReviewsByReviewTypes(type)
            ?.toResponse()
            ?: throw Exception("This review status $type not found")
    }

    suspend fun reviews(): List<ReviewResponse>? {
        return reviewRepository
            .reviews()
            .map { it.toResponse() }
    }
}

class ReviewMutations : Mutation {
    suspend fun deleteReview(id: String): String {
        return reviewRepository.deleteReviewById(ObjectId(id))
    }

    suspend fun updateReview(review: ReviewRequest): ReviewResponse? =
        reviewRepository.updateReview(review.toEntity())?.toResponse()


    suspend fun insertReview(user: ReviewRequest): ReviewResponse? =
        reviewRepository.insertReview(user.toEntity())?.toResponse()
}



val reviewSchema = toSchema(
    config = SchemaGeneratorConfig(
        supportedPackages = SUPPORTED_PACKAGES,
        additionalTypes = setOf(objectIdScalar, dateScalar, longScalar)

    ),
    queries = listOf(TopLevelObject(ReviewQuery())),
    mutations = listOf(TopLevelObject(ReviewMutations()))
)