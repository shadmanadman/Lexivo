package org.kmp.playground.lexivo.feedback.db.dao

import org.bson.types.ObjectId
import org.kmp.playground.lexivo.feedback.db.entity.FeedbackEntity

interface FeedbackDao {
    suspend fun feedbackByUserId(userId: ObjectId): List<FeedbackEntity>?
    suspend fun feedbackByReviewId(reviewId: ObjectId): List<FeedbackEntity>?
    suspend fun insertFeedback(feedback: FeedbackEntity): FeedbackEntity?
    suspend fun getFeedbackById(id: ObjectId): FeedbackEntity?
    suspend fun updateFeedback(feedback: FeedbackEntity): FeedbackEntity?
    suspend fun deleteFeedback(id: ObjectId): String?
}