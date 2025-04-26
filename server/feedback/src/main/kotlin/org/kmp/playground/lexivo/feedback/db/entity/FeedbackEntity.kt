package org.kmp.playground.lexivo.feedback.db.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.model.feedback.response.FeedbackCategory

data class FeedbackEntity(
    @BsonId
    val id: ObjectId = ObjectId(),
    val userId: String,
    val reviewId: String,
    val message: String,
    val rating: Int,
    val category: FeedbackCategory
)



