package org.kmp.playground.lexivo.review

import com.expediagroup.graphql.server.ktor.GraphQL
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.*
import org.kmp.playground.lexivo.SERVER_PORT
import org.kmp.playground.lexivo.review.route.ReviewMutations
import org.kmp.playground.lexivo.review.route.ReviewQuery
import org.kmp.playground.lexivo.review.route.SUPPORTED_PACKAGES
import org.kmp.playground.lexivo.review.route.reviewSchema

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
            reviewSchema
            packages = SUPPORTED_PACKAGES
            queries = listOf(
                ReviewQuery()
            )
            mutations = listOf(ReviewMutations())
        }
    }
}