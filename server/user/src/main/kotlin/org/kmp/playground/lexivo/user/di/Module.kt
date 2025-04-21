package org.kmp.playground.lexivo.user.di

import org.kmp.playground.lexivo.mlgate.core.db.DatabaseCore
import org.kmp.playground.lexivo.user.db.dao.UserDao
import org.kmp.playground.lexivo.user.db.dao.UserDaoImpl

object Module {
    private val database by lazy { DatabaseCore.userDb }

    fun userDao(): UserDao = UserDaoImpl(database)
}