package org.kmp.playground.lexivo.user.platform


class AndroidPlatformInfo : PlatformInfo {
    override val name: PlatformName = PlatformName.Android
}

actual fun platformDetails(): PlatformInfo = AndroidPlatformInfo()