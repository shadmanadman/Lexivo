package org.kmp.playground.lexivo.user.feature.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.kmp.playground.lexivo.user.feature.history.HistoryComponent
import org.kmp.playground.lexivo.user.feature.landing.LandingComponent
import org.kmp.playground.lexivo.user.feature.main.state.NavigationMenuState
import org.kmp.playground.lexivo.user.feature.main.viewmodel.NavigationMenuViewModel
import org.kmp.playground.lexivo.user.feature.team.TeamComponent
import org.kmp.playground.lexivo.user.feature.profile.ProfileComponent

interface MainNavigationMenuComponent {

    val pages: Value<ChildStack<*, MainNavMenuChild>>

    val bottomNavViewModel: NavigationMenuViewModel

    fun onState(state: NavigationMenuState)

    sealed interface MainNavMenuChild {
        data class Landing(
            val component: LandingComponent,
        ) : MainNavMenuChild

        data class History(
            val component: HistoryComponent,
        ) : MainNavMenuChild

        data class Team(
            val component: TeamComponent,
        ) : MainNavMenuChild

        data class Profile(
            val component: ProfileComponent,
        ) : MainNavMenuChild
    }
}