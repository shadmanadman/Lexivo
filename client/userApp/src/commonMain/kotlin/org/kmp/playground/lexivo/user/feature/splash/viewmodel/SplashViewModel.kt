package org.kmp.playground.lexivo.user.feature.splash.viewmodel

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.kmp.playground.lexivo.user.feature.splash.state.UIState

class SplashViewModel : InstanceKeeper.Instance {
    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)
    private val _uiState = MutableStateFlow<UIState>(UIState.Waiting)
    val uiState: StateFlow<UIState> = _uiState

    init {
        waitForSplashTime()
    }

    private fun waitForSplashTime() {
        viewModelScope.launch {
            delay(3000)
            _uiState.update {
                UIState.Finished
            }
        }
    }

}