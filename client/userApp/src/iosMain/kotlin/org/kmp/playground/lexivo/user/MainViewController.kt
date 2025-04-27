package org.kmp.playground.lexivo.user

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.kmp.playground.lexivo.user.feature.core.CoreApp
import org.kmp.playground.lexivo.user.feature.root.UserAppRootImpl

fun MainViewController() = ComposeUIViewController {
    val lifecycle = LifecycleRegistry()

    val root = UserAppRootImpl(componentContext = DefaultComponentContext(lifecycle))

    CoreApp(root = root)
}