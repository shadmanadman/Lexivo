package org.kmp.playground.lexivo.user

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Lexivo",
    ) {
        App()
    }
}