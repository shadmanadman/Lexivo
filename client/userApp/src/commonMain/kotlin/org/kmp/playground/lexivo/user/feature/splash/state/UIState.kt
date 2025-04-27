package org.kmp.playground.lexivo.user.feature.splash.state

sealed interface UIState {
    data object Waiting : UIState
    data object Finished : UIState
}