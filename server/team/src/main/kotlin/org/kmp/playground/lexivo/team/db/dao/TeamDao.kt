package org.kmp.playground.lexivo.team.db.dao

import org.bson.types.ObjectId
import org.kmp.playground.lexivo.team.db.entity.TeamEntity

interface TeamDao {
    suspend fun insertTeam(team: TeamEntity): TeamEntity?
    suspend fun getTeamById(id: ObjectId): TeamEntity?
    suspend fun getTeamsByOwner(ownerId: ObjectId): List<TeamEntity>
    suspend fun updateTeam(team: TeamEntity): TeamEntity?
    suspend fun deleteTeam(id: ObjectId): String?
}