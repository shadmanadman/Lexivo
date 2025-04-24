package org.kmp.playground.lexivo.user.route

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.dateScalar
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.longScalar
import org.kmp.playground.lexivo.mlgate.core.qraphql.scalar.objectIdScalar
import org.kmp.playground.lexivo.model.user.Request.UserRequest
import org.kmp.playground.lexivo.model.user.Response.UserResponse
import org.kmp.playground.lexivo.user.di.Module
import org.kmp.playground.lexivo.user.mapper.toEntity
import org.kmp.playground.lexivo.user.mapper.toResponse

val userRepository = Module.userDao()

val SUPPORTED_PACKAGES = listOf(
    "org.kmp.playground.lexivo.user",
    "org.kmp.playground.lexivo.user.route",
    "org.kmp.playground.lexivo.user.mapper",
    "org.kmp.playground.lexivo",
    "kotlin"
)

class UserQuery : Query {

    suspend fun userById(id: String): UserResponse? {
        return userRepository
            .findById(ObjectId(id))
            ?.toResponse()
            ?: throw Exception("Artist with id $id not found")
    }

    suspend fun users(): List<UserResponse>? {
        return userRepository
            .findAll()
            ?.map { it.toResponse() }
    }
}

class UserMutations : Mutation {
    suspend fun deleteUser(id: String): String {
        return userRepository.deleteById(ObjectId(id))
    }

    @OptIn(ExperimentalStdlibApi::class)
    suspend fun updateUser(id: String, user: UserRequest): UserResponse? {
        val updatedId = userRepository.updateOne(ObjectId(id), user.toEntity())
        return UserResponse(
            updatedId,
            user.email,
            user.name,
            user.role,
            user.teamId,
            user.createdAt,
            user.updatedAt
        )
    }

    suspend fun insertUser(user: UserRequest): UserResponse? {
        val insertedId = userRepository.insertOne(user.toEntity())
        return UserResponse(
            insertedId,
            user.email,
            user.name,
            user.role,
            user.teamId,
            user.createdAt,
            user.updatedAt
        )
    }

    suspend fun registerUserByEmail(user: UserRequest): UserResponse?{
        val insertedId = userRepository.insertOne(user.toEntity())
        return UserResponse(
            insertedId,
            user.email,
            user.name,
            user.role,
            user.teamId,
            user.createdAt,
            user.updatedAt
        )
    }

    suspend fun registerUserByOAuth(user: UserRequest): UserResponse?{
        val insertedId = userRepository.insertOne(user.toEntity())
        return UserResponse(
            insertedId,
            user.email,
            user.name,
            user.role,
            user.teamId,
            user.createdAt,
            user.updatedAt
        )
    }
}



val userSchema = toSchema(
    config = SchemaGeneratorConfig(
        supportedPackages = SUPPORTED_PACKAGES,
        additionalTypes = setOf(objectIdScalar, dateScalar, longScalar)

    ),
    queries = listOf(TopLevelObject(UserQuery())),
    mutations = listOf(TopLevelObject(UserMutations()))
)

