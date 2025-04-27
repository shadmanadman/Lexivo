package org.kmp.playground.lexivo.user.feature.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.moriatsushi.insetsx.SystemBarsBehavior
import com.moriatsushi.insetsx.rememberWindowInsetsController
import org.kmp.playground.lexivo.user.feature.root.UserAppRoot
import org.kmp.playground.lexivo.user.feature.root.UserAppRoot.MainDestinationChild
import org.kmp.playground.lexivo.user.feature.splash.ui.SplashScreen
import org.kmp.playground.lexivo.user.themes.AppColors

@Composable
fun CoreApp(root: UserAppRoot){
    val windowsInsets = rememberWindowInsetsController()

    LaunchedEffect(Unit) {
        windowsInsets?.setIsNavigationBarsVisible(false)
        windowsInsets?.setIsStatusBarsVisible(false)
        windowsInsets?.setSystemBarsBehavior(SystemBarsBehavior.Immersive)
    }

    val stack by root.backstack.subscribeAsState()

    Column(
        modifier = Modifier.fillMaxSize()
            .background(AppColors().background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Children(
            stack = stack,
            modifier = Modifier.weight(1f),
        ) { childCreated ->
            when (val child = childCreated.instance) {
                is MainDestinationChild.Splash -> SplashScreen(
                    child.component
                )

                is MainDestinationChild.Landing -> TODO()
            }
        }
    }
}