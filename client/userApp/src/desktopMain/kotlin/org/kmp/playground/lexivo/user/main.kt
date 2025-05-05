package org.kmp.playground.lexivo.user

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.kmp.playground.lexivo.user.feature.core.CoreApp
import org.kmp.playground.lexivo.user.feature.root.UserAppRoot
import org.kmp.playground.lexivo.user.feature.root.UserAppRootImpl

fun main() = application {
    val lifecycle = LifecycleRegistry()

    val root = UserAppRootImpl(componentContext = DefaultComponentContext(lifecycle))


    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP-EventManagementFullStack",
    ) {

        CoreApp(root = root)
    }

}