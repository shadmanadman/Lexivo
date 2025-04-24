package org.kmp.playground.lexivo.team.route

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.dateScalar
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.longScalar
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.objectIdScalar
import org.kmp.playground.lexivo.model.review.request.ReviewRequest
import org.kmp.playground.lexivo.model.review.response.ReviewResponse
import org.kmp.playground.lexivo.model.review.response.ReviewStatus
import org.kmp.playground.lexivo.model.review.response.ReviewType
import org.kmp.playground.lexivo.model.team.request.TeamRequest
import org.kmp.playground.lexivo.model.team.response.TeamResponse
import org.kmp.playground.lexivo.team.di.Module
import org.kmp.playground.lexivo.team.mapper.toEntity
import org.kmp.playground.lexivo.team.mapper.toResponse

val teamRepository = Module.teamDao()

val SUPPORTED_PACKAGES = listOf(
    "org.kmp.playground.lexivo.review",
    "org.kmp.playground.lexivo.review.route",
    "org.kmp.playground.lexivo.review.mapper",
    "org.kmp.playground.lexivo",
    "kotlin"
)

class TeamQuery : Query {

    suspend fun teamById(id: String): TeamResponse? {
        return teamRepository
            .getTeamById(ObjectId(id))
            ?.toResponse()
            ?: throw Exception("This id $id not found")
    }

    suspend fun teamByOwnerId(id: String): List<TeamResponse>? {
        return teamRepository
            .getTeamsByOwner(ObjectId(id))
            .map { it.toResponse() }
    }
}

class TeamMutations : Mutation {
    suspend fun deleteTeam(id: String): String? {
        return teamRepository.deleteTeam(ObjectId(id))
    }

    suspend fun updateTeam(team: TeamRequest): TeamResponse? =
        teamRepository.updateTeam(team.toEntity())?.toResponse()


    suspend fun insertTeam(team: TeamRequest): TeamResponse? =
        teamRepository.insertTeam(team.toEntity())?.toResponse()
}



val teamSchema = toSchema(
    config = SchemaGeneratorConfig(
        supportedPackages = SUPPORTED_PACKAGES,
        additionalTypes = setOf(objectIdScalar, dateScalar, longScalar)

    ),
    queries = listOf(TopLevelObject(TeamQuery())),
    mutations = listOf(TopLevelObject(TeamMutations()))
)