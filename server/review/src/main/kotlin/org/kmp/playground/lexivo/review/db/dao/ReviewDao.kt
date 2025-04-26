package org.kmp.playground.lexivo.review.db.dao

import kotlinx.coroutines.flow.Flow
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.model.review.response.ReviewStatus
import org.kmp.playground.lexivo.model.review.response.ReviewType
import org.kmp.playground.lexivo.review.db.entity.ReviewEntity

interface ReviewDao {
    /**
     * Inserts a new review into the database.
     *
     * @param review The review to insert.
     * @return The inserted review with its generated ID.
     */
    suspend fun insertReview(review: ReviewEntity): ReviewEntity?

    suspend fun reviews(): List<ReviewEntity>

    /**
     * Retrieves a review by its ID.
     *
     * @param id The ID of the review to retrieve.
     * @return The review, or null if not found.
     */
    suspend fun getReviewById(id: ObjectId): ReviewEntity?

    /**
     * Retrieves all reviews for a given team.
     *
     * @param teamId The ID of the team.
     * @return A Flow of reviews belonging to the team.
     */
    suspend fun getReviewsByTeamId(teamId: ObjectId): List<ReviewEntity>?

    /**
     * Retrieves all reviews for a given user.
     *
     * @param userId The ID of the team.
     * @return A Flow of reviews belonging to the team.
     */
    suspend fun getReviewsByUserId(userId: ObjectId): List<ReviewEntity>?

    /**
     * Updates an existing review.
     *
     * @param review The review to update.
     * @return True if the update was successful, false otherwise.
     */
    suspend fun updateReview(review: ReviewEntity): ReviewEntity?

    /**
     * Deletes a review by its ID.
     *
     * @param id The ID of the review to delete.
     * @return True if the deletion was successful, false otherwise.
     */
    suspend fun deleteReviewById(id: ObjectId): String

    /**
     * Retrieves all reviews with a specific status.
     *
     * @param status The status to filter by.
     * @return A Flow of reviews with the specified status.
     */
    suspend fun getReviewsByStatus(status: ReviewStatus): ReviewEntity?

    /**
     * Retrieves all reviews with specific review types.
     *
     * @param reviewTypes The list of review types to filter by.
     * @return A Flow of reviews with at least one of the specified review types.
     */
    suspend fun getReviewsByReviewTypes(reviewTypes: List<ReviewType>): ReviewEntity?
}