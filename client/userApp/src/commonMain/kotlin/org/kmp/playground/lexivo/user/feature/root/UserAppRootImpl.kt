package org.kmp.playground.lexivo.user.feature.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.kmp.playground.lexivo.user.feature.history.HistoryComponentImpl
import org.kmp.playground.lexivo.user.feature.landing.LandingComponentImpl
import org.kmp.playground.lexivo.user.feature.main.NavigationMenuComponent
import org.kmp.playground.lexivo.user.feature.main.NavigationMenuComponentImpl.BottomNavConfig
import org.kmp.playground.lexivo.user.feature.root.UserAppRoot.MainDestinationChild
import org.kmp.playground.lexivo.user.feature.root.UserAppRoot.MainDestinationChild.*
import org.kmp.playground.lexivo.user.feature.root.UserAppRootImpl.MainNavigationConfig
import org.kmp.playground.lexivo.user.feature.splash.SplashComponentImpl
import org.kmp.playground.lexivo.user.feature.team.TeamComponentImpl
import org.kmp.playground.lexivo.user.feature.user.ProfileComponentImpl
import org.kmp.playground.lexivo.user.platform.isMobileDevice

class UserAppRootImpl(private val componentContext: ComponentContext) : UserAppRoot,
    ComponentContext by componentContext {

    private val mainDispatcher = CoroutineScope(Dispatchers.Main)

    private val navigation = StackNavigation<MainNavigationConfig>()

    private val _backstack = this.childStack(
        source = navigation,
        serializer = MainNavigationConfig.serializer(),
        initialConfiguration = setupInitialConfigurationBasedOnPlatform(),
        handleBackButton = true,
    ) { config, context ->
        createChildFactory(
            config = config, componentContext = context
        )
    }

    override val backstack = _backstack


    private fun createChildFactory(
        config: MainNavigationConfig,
        componentContext: ComponentContext
    ): MainDestinationChild {
        return when (config) {
            MainNavigationConfig.Landing -> landingComponentBuild(componentContext)
            MainNavigationConfig.Splash ->splashComponentBuild(componentContext)
            MainNavigationConfig.History -> historyComponentBuild(componentContext)
            MainNavigationConfig.Profile -> profileComponentBuild(componentContext)
            MainNavigationConfig.Team -> teamComponentBuild(componentContext)
        }
    }

    private fun splashComponentBuild(
        context: ComponentContext
    ) = Splash(SplashComponentImpl(
        componentContext = context,
        onSplashTimeFinished = {
            mainDispatcher.launch {
            }
        }))


    fun landingComponentBuild(
        context: ComponentContext
    ) = Landing(LandingComponentImpl(componentContext = context))

    fun historyComponentBuild(
        context: ComponentContext
    ) =History(HistoryComponentImpl(componentContext = context))

    fun teamComponentBuild(
        context: ComponentContext
    ) = Team(TeamComponentImpl(componentContext = context))


    fun profileComponentBuild(
        context: ComponentContext
    ) = Profile(ProfileComponentImpl(componentContext = context))



    @Serializable
    sealed class MainNavigationConfig {
        @Serializable
        data object Splash : MainNavigationConfig()

        @Serializable
        data object Landing : MainNavigationConfig()

        @Serializable
        data object History : MainNavigationConfig()

        @Serializable
        data object Team : MainNavigationConfig()

        @Serializable
        data object Profile : MainNavigationConfig()
    }
}

fun setupInitialConfigurationBasedOnPlatform(): MainNavigationConfig {
    return if (isMobileDevice())
        MainNavigationConfig.Splash
    else
        MainNavigationConfig.Landing
}