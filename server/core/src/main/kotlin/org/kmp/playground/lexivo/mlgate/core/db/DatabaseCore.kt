package org.kmp.playground.lexivo.mlgate.core.db

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase

object DatabaseCore {
    private val port: Int = System.getenv("MONGO_PORT")?.toIntOrNull() ?: 27017

    private val client: MongoClient by lazy {
        MongoClient.create("mongodb://localhost:$port")
    }

    // Map of module/database names
    fun getDatabase(name: String): MongoDatabase {
        return client.getDatabase(name)
    }

    // Optional helper for common databases
    val userDb: MongoDatabase get() = getDatabase("userDatabase")
    val authDb: MongoDatabase get() = getDatabase("authDatabase")
    val teamDb: MongoDatabase get() = getDatabase("teamDatabase")

    init {
        Runtime.getRuntime().addShutdownHook(Thread {
            close()
        })
    }

    fun close() {
        client.close()
    }
}