package org.kmp.playground.lexivo.notification.di

import org.kmp.playground.lexivo.mlgate.core.db.DatabaseCore
import org.kmp.playground.lexivo.notification.db.dao.NotificationDao
import org.kmp.playground.lexivo.notification.db.dao.NotificationDaoImpl

object Module {
    private val database by lazy { DatabaseCore.notificationDb }

    fun notificationDao(): NotificationDao = NotificationDaoImpl(database)
}