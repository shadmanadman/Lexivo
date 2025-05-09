package org.kmp.playground.lexivo.mlgate.core

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import org.kmp.playground.lexivo.SERVER_PORT

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        get("/") {
        }
    }
}