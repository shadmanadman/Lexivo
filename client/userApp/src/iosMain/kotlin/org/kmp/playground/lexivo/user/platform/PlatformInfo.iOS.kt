package org.kmp.playground.lexivo.user.platform

class IosPlatformInfo : PlatformInfo {
    override val name: PlatformName = PlatformName.iOS
}

actual fun platformDetails(): PlatformInfo = IosPlatformInfo()