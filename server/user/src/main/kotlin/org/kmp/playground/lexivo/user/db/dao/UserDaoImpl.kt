package org.kmp.playground.lexivo.user.db.dao

import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.BsonValue
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.user.db.entity.UserEntity

class UserDaoImpl(private val mongoDatabase: MongoDatabase) : UserDao {
    companion object {
        const val ARTISTS_COLLECTION = "artists"
    }

    override suspend fun findAll(): List<UserEntity> =
        mongoDatabase.getCollection<UserEntity>(ARTISTS_COLLECTION).find().toList()

    override suspend fun findById(objectId: ObjectId): UserEntity? =
        mongoDatabase.getCollection<UserEntity>(ARTISTS_COLLECTION).withDocumentClass<UserEntity>()
            .find(Filters.eq("_id", objectId))
            .firstOrNull()


    override suspend fun insertOne(artists: UserEntity): BsonValue? {
        try {
            val result = mongoDatabase.getCollection<UserEntity>(ARTISTS_COLLECTION).insertOne(
                artists
            )

            return result.insertedId
        } catch (e: MongoException) {
            System.err.println("Unable to insert due to an error: $e")
        }

        return null
    }

    override suspend fun deleteById(objectId: ObjectId): Long {
        try {
            val result = mongoDatabase.getCollection<UserEntity>(ARTISTS_COLLECTION)
                .deleteOne(Filters.eq("_id", objectId))
            return result.deletedCount
        } catch (e: MongoException) {
            System.err.println("Unable to delete due to an error: $e")
        }

        return 0
    }

    override suspend fun updateOne(objectId: ObjectId, artists: UserEntity): Long {
        try {
            val query = Filters.eq("_id", objectId)
            val updates = Updates.combine(
                Updates.set(UserEntity::name.name, artists.name),
                Updates.set(UserEntity::email.name, artists.email),
                Updates.set(UserEntity::provider.name, artists.provider),
                Updates.set(UserEntity::avatar.name, artists.avatar),
                Updates.set(UserEntity::providerId.name, artists.providerId),
                Updates.set(UserEntity::role.name, artists.role),
                Updates.set(UserEntity::hashedPassword.name, artists.hashedPassword),
                Updates.set(UserEntity::teamId.name, artists.teamId),
                Updates.set(UserEntity::updatedAt.name, artists.updatedAt)
            )

            val options = UpdateOptions().upsert(true)

            val result =
                mongoDatabase.getCollection<UserEntity>(ARTISTS_COLLECTION)
                    .updateOne(query, updates, options)

            return result.modifiedCount
        } catch (e: MongoException) {
            System.err.println("Unable to update due to an error: $e")
        }

        return 0
    }

}