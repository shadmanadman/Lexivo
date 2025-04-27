package org.kmp.playground.lexivo.user.feature.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.kmp.playground.lexivo.user.feature.root.UserAppRoot.MainDestinationChild
import org.kmp.playground.lexivo.user.feature.splash.SplashComponentImpl

class UserAppRootImpl(private val componentContext: ComponentContext): UserAppRoot, ComponentContext by componentContext {

    private val mainDispatcher = CoroutineScope(Dispatchers.Main)

    private val navigation = StackNavigation<MainNavigationConfig>()

    private val _backstack = this.childStack(
        source = navigation,
        serializer = MainNavigationConfig.serializer(),
        initialConfiguration = MainNavigationConfig.Splash,
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
            MainNavigationConfig.Landing -> TODO()
            MainNavigationConfig.Splash -> MainDestinationChild.Splash(
                splashComponentBuild(componentContext)
            )
        }
    }

    private fun splashComponentBuild(
        context: ComponentContext
    ) = SplashComponentImpl(componentContext = context,
        onSplashTimeFinished = {
            mainDispatcher.launch {
            }
        })

    @Serializable
    sealed class MainNavigationConfig {
        @Serializable
        data object Splash : MainNavigationConfig()
        @Serializable
        data object Landing : MainNavigationConfig()
    }
}