package org.kmp.playground.lexivo.user.feature.splash

import org.kmp.playground.lexivo.user.feature.splash.viewmodel.SplashViewModel

interface SplashComponent {
    val splashViewModel: SplashViewModel

    fun onSplashTimeFinish()
}