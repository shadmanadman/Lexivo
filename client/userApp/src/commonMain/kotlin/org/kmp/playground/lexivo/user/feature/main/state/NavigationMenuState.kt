package org.kmp.playground.lexivo.user.feature.main.state

import org.kmp.playground.lexivo.user.feature.root.UserAppRootImpl

sealed class NavigationMenuState {
    data class NavigateToMainChild(val child: UserAppRootImpl.MainNavigationConfig) : NavigationMenuState()
    data class NavigateToSearch(val child: UserAppRootImpl.MainNavigationConfig) : NavigationMenuState()
    object OpenLanding : NavigationMenuState()
    object OpenHistory : NavigationMenuState()
    object OpenTeam : NavigationMenuState()
    object OpenProfile : NavigationMenuState()
}