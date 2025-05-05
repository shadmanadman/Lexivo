package org.kmp.playground.lexivo.user.feature.splash.ui

import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import org.kmp.playground.lexivo.user.feature.splash.SplashComponent
import org.kmp.playground.lexivo.user.feature.splash.state.UIState

@Composable
fun SplashScreen(component: SplashComponent) {
    val splashViewModel = component.splashViewModel
    val splashTime by splashViewModel.uiState.collectAsState()

    when (splashTime) {
        UIState.Waiting -> {
            SplashElement()
        }

        is UIState.Finished -> {
        }
    }
}


@Composable
fun SplashElement() {
    val scaleAnimation by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 2000, easing = EaseInBounce)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Lexivo", modifier = Modifier.scale(scaleAnimation), textAlign = TextAlign.Center)
    }
}