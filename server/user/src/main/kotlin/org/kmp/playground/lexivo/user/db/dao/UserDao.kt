package org.kmp.playground.lexivo.user.db.dao

import org.bson.BsonValue
import org.bson.types.ObjectId
import org.kmp.playground.lexivo.user.db.entity.UserEntity

interface UserDao {

    suspend fun findAll(): List<UserEntity>?

    suspend fun findById(objectId: ObjectId): UserEntity?

    suspend fun insertOne(user: UserEntity): BsonValue?

    suspend fun deleteById(objectId: ObjectId): Long

    suspend fun updateOne(objectId: ObjectId, user: UserEntity): Long
}