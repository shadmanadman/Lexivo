package org.kmp.playground.lexivo.user.platform

class DesktopPlatformInfo : PlatformInfo {
    override val name: PlatformName = PlatformName.Desktop
}

actual fun platformDetails(): PlatformInfo = DesktopPlatformInfo()