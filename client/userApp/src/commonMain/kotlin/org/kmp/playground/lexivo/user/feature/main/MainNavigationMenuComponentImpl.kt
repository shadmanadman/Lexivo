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
import org.kmp.playground.lexivo.user.feature.team.TeamComponentImpl
import org.kmp.playground.lexivo.user.feature.profile.ProfileComponentImpl

const val PAGE_KEY_BOTTOM_NAV = "bottomNavPageKey"

class MainNavigationMenuComponentImpl(val componentContext: ComponentContext) :
    ComponentContext by componentContext, MainNavigationMenuComponent {


    private val navigation = StackNavigation<MainNavMenuConfig>()

    private val _pages =
        childStack(
            source = navigation,
            serializer = MainNavMenuConfig.serializer(),
            initialConfiguration = MainNavMenuConfig.Landing,
            handleBackButton = true,
            childFactory = ::createChildPageFactor,
            key = PAGE_KEY_BOTTOM_NAV
        )
    override val pages: Value<ChildStack<*, MainNavigationMenuComponent.MainNavMenuChild>> get() = _pages

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
        config: MainNavMenuConfig,
        componentContext: ComponentContext
    ): MainNavigationMenuComponent.MainNavMenuChild {
        return when (config) {
            MainNavMenuConfig.History -> historyComponentBuild(componentContext)
            MainNavMenuConfig.Landing -> landingComponentBuild(componentContext)
            MainNavMenuConfig.Team -> teamComponentBuild(componentContext)
            MainNavMenuConfig.Profile -> profileComponentBuild(componentContext)
        }
    }

    fun landingComponentBuild(
        context: ComponentContext
    ) = MainNavigationMenuComponent.MainNavMenuChild.Landing(LandingComponentImpl(componentContext = context))

    fun historyComponentBuild(
        context: ComponentContext
    ) =MainNavigationMenuComponent.MainNavMenuChild.History(HistoryComponentImpl(componentContext = context))

    fun teamComponentBuild(
        context: ComponentContext
    ) = MainNavigationMenuComponent.MainNavMenuChild.Team(TeamComponentImpl(componentContext = context))


    fun profileComponentBuild(
        context: ComponentContext
    ) = MainNavigationMenuComponent.MainNavMenuChild.Profile(ProfileComponentImpl(componentContext = context))

    @Serializable
    sealed class MainNavMenuConfig {
        @Serializable
        data object Landing : MainNavMenuConfig()

        @Serializable
        data object History : MainNavMenuConfig()

        @Serializable
        data object Team : MainNavMenuConfig()

        @Serializable
        data object Profile : MainNavMenuConfig()
    }
}