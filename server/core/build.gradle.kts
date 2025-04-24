plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "org.kmp.playground.lexivo.server.core"
version = "1.0.0"
application {
    mainClass.set("org.kmp.playground.lexivo.core.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    api(projects.shared)
    api(libs.logback)
    api(libs.ktor.server.core)
    api(libs.ktor.server.netty)
    api(libs.ktor.server.test.host)
    api(libs.kotlin.test.junit)
    api(libs.ktor.json)
    api(libs.kotlin.test)
    // MongoDB
    api(libs.mongodb.driver)
    // GraphQL
    api(libs.graphql.kotlin.server)
    api(libs.graphql.kotlin.ktor.server)
}