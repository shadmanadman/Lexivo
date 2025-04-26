package org.kmp.playground.lexivo.feedback

import com.expediagroup.graphql.server.ktor.GraphQL
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.*
import org.kmp.playground.lexivo.SERVER_PORT
import org.kmp.playground.lexivo.feedback.route.FeedbackMutations
import org.kmp.playground.lexivo.feedback.route.FeedbackQuery
import org.kmp.playground.lexivo.feedback.route.SUPPORTED_PACKAGES
import org.kmp.playground.lexivo.feedback.route.feedbackSchema

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
            feedbackSchema
            packages = SUPPORTED_PACKAGES
            queries = listOf(
                FeedbackQuery()
            )
            mutations = listOf(FeedbackMutations())
        }
    }
}