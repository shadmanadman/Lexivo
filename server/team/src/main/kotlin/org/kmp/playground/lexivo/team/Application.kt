package org.kmp.playground.lexivo.team

import com.expediagroup.graphql.server.ktor.GraphQL
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.*
import org.kmp.playground.lexivo.SERVER_PORT
import org.kmp.playground.lexivo.team.route.SUPPORTED_PACKAGES
import org.kmp.playground.lexivo.team.route.TeamMutations
import org.kmp.playground.lexivo.team.route.TeamQuery
import org.kmp.playground.lexivo.team.route.teamSchema

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
            teamSchema
            packages = SUPPORTED_PACKAGES
            queries = listOf(
                TeamQuery()
            )
            mutations = listOf(TeamMutations())
        }
    }
}