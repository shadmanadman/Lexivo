package org.kmp.playground.lexivo.review.di

import org.kmp.playground.lexivo.mlgate.core.db.DatabaseCore
import org.kmp.playground.lexivo.review.db.dao.ReviewDao
import org.kmp.playground.lexivo.review.db.dao.ReviewDaoImpl

object Module {
    private val database by lazy { DatabaseCore.reviewDb }

    fun reviewDao(): ReviewDao = ReviewDaoImpl(database)
}