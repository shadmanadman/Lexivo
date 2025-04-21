plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "org.kmp.playground.lexivo.server.user"
version = "1.0.0"
application {
    mainClass.set("org.kmp.playground.lexivo.user.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.server.core)
}