package org.kmp.playground.lexivo.user.feature.splash

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.kmp.playground.lexivo.user.feature.splash.viewmodel.SplashViewModel

class SplashComponentImpl(
    componentContext: ComponentContext,
    val onSplashTimeFinished: () -> Unit
) : SplashComponent, ComponentContext by componentContext {
    override val splashViewModel: SplashViewModel
        get() = instanceKeeper.getOrCreate { SplashViewModel() }

    override fun onSplashTimeFinish() {
        onSplashTimeFinished()
    }
}