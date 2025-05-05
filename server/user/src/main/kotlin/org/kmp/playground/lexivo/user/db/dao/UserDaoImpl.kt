package org.kmp.playground.lexivo.user.db.dao

import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.user.db.entity.UserEntity

class UserDaoImpl(private val mongoDatabase: MongoDatabase) : UserDao {
    companion object {
        const val USER_COLLECTION = "user"
    }

    override suspend fun findAll(): List<UserEntity> =
        mongoDatabase.getCollection<UserEntity>(USER_COLLECTION).find().toList()

    override suspend fun findById(objectId: ObjectId): UserEntity? =
        mongoDatabase.getCollection<UserEntity>(USER_COLLECTION).withDocumentClass<UserEntity>()
            .find(Filters.eq("_id", objectId))
            .firstOrNull()


    override suspend fun insertOne(user: UserEntity): String? {
        try {
            val result = mongoDatabase.getCollection<UserEntity>(USER_COLLECTION).insertOne(
                user
            )

            return result.insertedId?.asObjectId()?.value.toString()
        } catch (e: MongoException) {
            System.err.println("Unable to insert due to an error: $e")
        }

        return null
    }

    override suspend fun deleteById(objectId: ObjectId): String {
        try {
            val result = mongoDatabase.getCollection<UserEntity>(USER_COLLECTION)
                .deleteOne(Filters.eq("_id", objectId))
            return result.deletedCount.toString()
        } catch (e: MongoException) {
            System.err.println("Unable to delete due to an error: $e")
        }

        return "-1"
    }

    override suspend fun updateOne(objectId: ObjectId, user: UserEntity): String {
        try {
            val query = Filters.eq("_id", objectId)
            val updates = Updates.combine(
                Updates.set(UserEntity::name.name, user.name),
                Updates.set(UserEntity::email.name, user.email),
                Updates.set(UserEntity::provider.name, user.provider),
                Updates.set(UserEntity::avatar.name, user.avatar),
                Updates.set(UserEntity::providerId.name, user.providerId),
                Updates.set(UserEntity::role.name, user.role),
                Updates.set(UserEntity::hashedPassword.name, user.hashedPassword),
                Updates.set(UserEntity::teamId.name, user.teamId),
                Updates.set(UserEntity::updatedAt.name, user.updatedAt)
            )

            val options = UpdateOptions().upsert(true)

            val result =
                mongoDatabase.getCollection<UserEntity>(USER_COLLECTION)
                    .updateOne(query, updates, options)

            return result.modifiedCount.toString()
        } catch (e: MongoException) {
            System.err.println("Unable to update due to an error: $e")
        }

        return "-1"
    }

}