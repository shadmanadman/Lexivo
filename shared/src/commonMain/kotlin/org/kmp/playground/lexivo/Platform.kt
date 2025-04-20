package org.kmp.playground.lexivo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform