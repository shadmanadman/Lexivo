package org.kmp.playground.lexivo.user.platform

enum class PlatformName {
    Android,
    iOS,
    Desktop,
    Wasm
}

interface PlatformInfo {
    val name: PlatformName
}

expect fun platformDetails(): PlatformInfo

fun isMobileDevice(): Boolean {
    return platformDetails().name == PlatformName.Android || platformDetails().name == PlatformName.iOS
}