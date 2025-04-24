package org.kmp.playground.lexivo.team.di

import org.kmp.playground.lexivo.mlgate.core.db.DatabaseCore
import org.kmp.playground.lexivo.team.db.dao.TeamDao
import org.kmp.playground.lexivo.team.db.dao.TeamDaoImpl

object Module {
    private val database by lazy { DatabaseCore.teamDb }

    fun teamDao(): TeamDao = TeamDaoImpl(database)
}