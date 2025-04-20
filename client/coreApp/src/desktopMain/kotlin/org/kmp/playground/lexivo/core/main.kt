package org.kmp.playground.lexivo.core

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