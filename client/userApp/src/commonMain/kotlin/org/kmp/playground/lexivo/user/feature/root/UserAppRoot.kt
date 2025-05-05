package org.kmp.playground.lexivo.user.feature.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.kmp.playground.lexivo.user.feature.history.HistoryComponent
import org.kmp.playground.lexivo.user.feature.landing.LandingComponent
import org.kmp.playground.lexivo.user.feature.main.MainNavigationMenuComponent
import org.kmp.playground.lexivo.user.feature.splash.SplashComponent
import org.kmp.playground.lexivo.user.feature.team.TeamComponent
import org.kmp.playground.lexivo.user.feature.profile.ProfileComponent

interface UserAppRoot {
    val backstack: Value<ChildStack<*, MainDestinationChild>>

    sealed class MainDestinationChild {
        class Splash(
            val component: SplashComponent,
        ) : MainDestinationChild()

        class MainNavigationMenu(
            val component: MainNavigationMenuComponent,
        ) : MainDestinationChild()
    }
}