package org.kmp.playground.lexivo.user.platform


class WasmPlatformInfo : PlatformInfo {
    override val name: PlatformName = PlatformName.Wasm
}

actual fun platformDetails(): PlatformInfo = WasmPlatformInfo()