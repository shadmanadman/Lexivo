plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinSerialization)
    application
}

group = "org.kmp.playground.lexivo.server.user"
version = "1.0.0"
application {
    mainClass.set("org.kmp.playground.lexivo.user.ApplicationKt")
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