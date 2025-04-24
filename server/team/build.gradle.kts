plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "org.kmp.playground.lexivo.server.team"
version = "1.0.0"
application {
    mainClass.set("org.kmp.playground.lexivo.team.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}
sourceSets {
    test {
        kotlin.srcDirs("src/test")
    }
}
dependencies {
    implementation(projects.server.core)
}