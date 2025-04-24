package org.kmp.playground.lexivo.team.db.dao

import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.team.db.entity.TeamEntity
import java.time.LocalDateTime

class TeamDaoImpl(val mongoDatabase: MongoDatabase): TeamDao  {
    companion object {
        const val TEAM_COLLECTION = "team"
    }

    override suspend fun insertTeam(team: TeamEntity): TeamEntity? {
        try {
            val result = mongoDatabase.getCollection<TeamEntity>(TEAM_COLLECTION).insertOne(
                team
            )

            return result.insertedId?.asObjectId()?.value?.let { objectId ->
                mongoDatabase.getCollection<TeamEntity>(TEAM_COLLECTION)
                    .find(Filters.eq("_id", objectId))
                    .firstOrNull()
            }
        } catch (e: MongoException) {
            System.err.println("Unable to insert due to an error: $e")
        }

        return null
    }

    override suspend fun getTeamById(id: ObjectId): TeamEntity? =
        mongoDatabase.getCollection<TeamEntity>(TEAM_COLLECTION).withDocumentClass<TeamEntity>()
            .find(Filters.eq("_id", id))
            .firstOrNull()
    

    override suspend fun getTeamsByOwner(ownerId: ObjectId): List<TeamEntity> =
        mongoDatabase.getCollection<TeamEntity>(TEAM_COLLECTION).withDocumentClass<TeamEntity>()
            .find(Filters.eq("ownerId", ownerId))
            .toList()

    override suspend fun updateTeam(
        team: TeamEntity
    ): TeamEntity? {
        try {
            val query = Filters.eq("_id", team.id)
            val updates = Updates.combine(
                Updates.set("name", team.name),
                Updates.set("ownerId", team.ownerId),
                Updates.set("members", team.members),
                Updates.set("styleGuide", team.styleGuide),
                Updates.set("updatedAt", LocalDateTime.now())
            )
            val options = UpdateOptions().upsert(true)

            mongoDatabase.getCollection<TeamEntity>(TEAM_COLLECTION)
                .updateOne(query, updates, options)

            return mongoDatabase.getCollection<TeamEntity>(TEAM_COLLECTION)
                .find(query)
                .firstOrNull()
        } catch (e: MongoException) {
            System.err.println("Unable to update entity due to an error: $e")
        }
        return null
    }

    override suspend fun deleteTeam(id: ObjectId): String? {
        try {
            val result = mongoDatabase.getCollection<TeamEntity>(TEAM_COLLECTION)
                .deleteOne(Filters.eq("_id", id))
            return result.deletedCount.toString()
        } catch (e: MongoException) {
            System.err.println("Unable to delete due to an error: $e")
        }

        return "-1"
    }
}