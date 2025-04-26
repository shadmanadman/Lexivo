package org.kmp.playground.lexivo.feedback.di

import org.kmp.playground.lexivo.feedback.db.dao.FeedbackDao
import org.kmp.playground.lexivo.feedback.db.dao.FeedbackDaoImpl
import org.kmp.playground.lexivo.mlgate.core.db.DatabaseCore

object Module {
    private val database by lazy { DatabaseCore.feedbackDb }

    fun feedbackDao(): FeedbackDao = FeedbackDaoImpl(database)
}