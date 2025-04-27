package org.kmp.playground.lexivo.user.feature.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.kmp.playground.lexivo.user.feature.landing.LandingComponent
import org.kmp.playground.lexivo.user.feature.splash.SplashComponent

interface UserAppRoot {
    val backstack: Value<ChildStack<*, MainDestinationChild>>

    sealed class MainDestinationChild {
        class Splash(
            val component: SplashComponent,
        ) : MainDestinationChild()

        class Landing(
            val component: LandingComponent,
        ) : MainDestinationChild()
    }
}