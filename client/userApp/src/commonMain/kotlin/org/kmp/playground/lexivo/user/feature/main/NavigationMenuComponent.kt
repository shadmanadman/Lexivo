package org.kmp.playground.lexivo.user.feature.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.kmp.playground.lexivo.user.feature.history.HistoryComponent
import org.kmp.playground.lexivo.user.feature.landing.LandingComponent
import org.kmp.playground.lexivo.user.feature.main.state.NavigationMenuState
import org.kmp.playground.lexivo.user.feature.main.viewmodel.NavigationMenuViewModel
import org.kmp.playground.lexivo.user.feature.root.UserAppRootImpl
import org.kmp.playground.lexivo.user.feature.team.TeamComponent
import org.kmp.playground.lexivo.user.feature.user.ProfileComponent

interface NavigationMenuComponent {

    val pages: Value<ChildStack<*, BottomNavChild>>

    val bottomNavViewModel: NavigationMenuViewModel

    fun onState(state: NavigationMenuState)

    sealed interface BottomNavChild {
        data class Landing(
            val component: LandingComponent,
        ) : BottomNavChild

        data class History(
            val component: HistoryComponent,
        ) : BottomNavChild

        data class Team(
            val component: TeamComponent,
        ) : BottomNavChild

        data class Profile(
            val component: ProfileComponent,
        ) : BottomNavChild
    }
}