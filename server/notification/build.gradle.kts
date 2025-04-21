plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "org.kmp.playground.lexivo.server.notification"
version = "1.0.0"
application {
    mainClass.set("org.kmp.playground.lexivo.notification.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.server.core)
}