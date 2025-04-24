package org.kmp.playground.lexivo.user

import com.expediagroup.graphql.server.ktor.GraphQL
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import org.kmp.playground.lexivo.SERVER_PORT
import org.kmp.playground.lexivo.user.route.SUPPORTED_PACKAGES
import org.kmp.playground.lexivo.user.route.UserMutations
import org.kmp.playground.lexivo.user.route.UserQuery
import org.kmp.playground.lexivo.user.route.userSchema

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation){
        json()
    }
    install(GraphQL){
        schema {
            userSchema
            packages = SUPPORTED_PACKAGES
            queries = listOf(
                UserQuery()
            )
            mutations = listOf(UserMutations())
        }
    }
}