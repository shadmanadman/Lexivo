package org.kmp.playground.lexivo.user.feature.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.serialization.Serializable
import org.kmp.playground.lexivo.user.feature.history.HistoryComponentImpl
import org.kmp.playground.lexivo.user.feature.landing.LandingComponentImpl
import org.kmp.playground.lexivo.user.feature.main.state.NavigationMenuState
import org.kmp.playground.lexivo.user.feature.main.viewmodel.NavigationMenuViewModel
import org.kmp.playground.lexivo.user.feature.root.UserAppRoot
import org.kmp.playground.lexivo.user.feature.root.UserAppRootImpl
import org.kmp.playground.lexivo.user.feature.team.TeamComponentImpl
import org.kmp.playground.lexivo.user.feature.user.ProfileComponentImpl

const val PAGE_KEY_BOTTOM_NAV = "bottomNavPageKey"

class NavigationMenuComponentImpl(val componentContext: ComponentContext) :
    ComponentContext by componentContext, NavigationMenuComponent {


    private val navigation = StackNavigation<BottomNavConfig>()

    private val _pages =
        childStack(
            source = navigation,
            serializer = BottomNavConfig.serializer(),
            initialConfiguration = BottomNavConfig.Landing,
            handleBackButton = true,
            childFactory = ::createChildPageFactor,
            key = PAGE_KEY_BOTTOM_NAV
        )
    override val pages: Value<ChildStack<*, NavigationMenuComponent.BottomNavChild>> get() = _pages

    override val bottomNavViewModel: NavigationMenuViewModel
        get() = instanceKeeper.getOrCreate { NavigationMenuViewModel() }


    override fun onState(state: NavigationMenuState) {
        when (state) {
            is NavigationMenuState.NavigateToMainChild -> {
                // handle navigation
            }
            is NavigationMenuState.NavigateToSearch -> {
                // handle search
            }
            NavigationMenuState.OpenLanding -> {
                // open landing
            }
            NavigationMenuState.OpenHistory -> {
                // open history
            }
            NavigationMenuState.OpenTeam -> {
                // open team
            }
            NavigationMenuState.OpenProfile -> {
                // open profile
            }
        }
    }

    private fun createChildPageFactor(
        config: BottomNavConfig,
        componentContext: ComponentContext
    ): NavigationMenuComponent.BottomNavChild {
        return when (config) {
            BottomNavConfig.History -> historyComponentBuild(componentContext)
            BottomNavConfig.Landing -> landingComponentBuild(componentContext)
            BottomNavConfig.Team -> teamComponentBuild(componentContext)
            BottomNavConfig.Profile -> profileComponentBuild(componentContext)
        }
    }

    fun landingComponentBuild(
        context: ComponentContext
    ) = NavigationMenuComponent.BottomNavChild.Landing(LandingComponentImpl(componentContext = context))

    fun historyComponentBuild(
        context: ComponentContext
    ) =NavigationMenuComponent.BottomNavChild.History(HistoryComponentImpl(componentContext = context))

    fun teamComponentBuild(
        context: ComponentContext
    ) = NavigationMenuComponent.BottomNavChild.Team(TeamComponentImpl(componentContext = context))


    fun profileComponentBuild(
        context: ComponentContext
    ) = NavigationMenuComponent.BottomNavChild.Profile(ProfileComponentImpl(componentContext = context))

    @Serializable
    sealed class BottomNavConfig {
        @Serializable
        data object Landing : BottomNavConfig()

        @Serializable
        data object History : BottomNavConfig()

        @Serializable
        data object Team : BottomNavConfig()

        @Serializable
        data object Profile : BottomNavConfig()
    }
}