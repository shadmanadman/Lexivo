package org.kmp.playground.lexivo.notification

import com.expediagroup.graphql.server.ktor.GraphQL
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.*
import org.kmp.playground.lexivo.SERVER_PORT
import org.kmp.playground.lexivo.notification.route.NotificationMutations
import org.kmp.playground.lexivo.notification.route.NotificationQuery
import org.kmp.playground.lexivo.notification.route.SUPPORTED_PACKAGES
import org.kmp.playground.lexivo.notification.route.notificationSchema

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
            notificationSchema
            packages = SUPPORTED_PACKAGES
            queries = listOf(
                NotificationQuery()
            )
            mutations = listOf(NotificationMutations())
        }
    }
}