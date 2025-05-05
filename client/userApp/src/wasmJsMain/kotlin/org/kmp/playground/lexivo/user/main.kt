package org.kmp.playground.lexivo.user

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import kotlinx.browser.document
import org.kmp.playground.lexivo.user.feature.core.CoreApp
import org.kmp.playground.lexivo.user.feature.root.UserAppRootImpl

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        val lifecycle = LifecycleRegistry()

        val root = UserAppRootImpl(componentContext = DefaultComponentContext(lifecycle))
        CoreApp(root = root)
    }
}