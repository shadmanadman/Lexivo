package org.kmp.playground.lexivo.user.feature.main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

import org.jetbrains.compose.resources.StringResource
import org.kmp.playground.lexivo.core.resources.StringShared
import org.kmp.playground.lexivo.user.feature.main.MainNavigationMenuComponent
import org.kmp.playground.lexivo.user.feature.main.state.NavigationMenuState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.kmp.playground.lexivo.core.resources.DrawableShared
import org.kmp.playground.lexivo.core.resources.asDrawable
import org.kmp.playground.lexivo.core.resources.asString
import org.kmp.playground.lexivo.user.feature.history.ui.HistoryScreen
import org.kmp.playground.lexivo.user.feature.landing.ui.LandingScreen
import org.kmp.playground.lexivo.user.feature.profile.ui.ProfileScreen
import org.kmp.playground.lexivo.user.feature.team.ui.TeamScreen
import org.kmp.playground.lexivo.user.platform.isMobileDevice
import org.kmp.playground.lexivo.user.themes.AppTypography

data class BottomNavScreensState(
    val id: RootNavigationMenuId,
    val title: StringResource? = null,
    val openScreen: () -> Unit,
    val isSelected: Boolean,
    val icon: DrawableResource? = null
)

enum class RootNavigationMenuId {
    Landing,
    Team,
    History,
    Profile
}

/**
 * This will check if the given root navigation id matches the provided bottom navigation child,
 * so when user press the back button the associated tab should be selected.
 */
fun RootNavigationMenuId.matchesMainNavMenuChild(bottomNavChild: MainNavigationMenuComponent.MainNavMenuChild): Boolean {
    return this == RootNavigationMenuId.Landing && bottomNavChild is MainNavigationMenuComponent.MainNavMenuChild.Landing
            || this == RootNavigationMenuId.Team && bottomNavChild is MainNavigationMenuComponent.MainNavMenuChild.Team
            || this == RootNavigationMenuId.History && bottomNavChild is MainNavigationMenuComponent.MainNavMenuChild.History
            || this == RootNavigationMenuId.Profile && bottomNavChild is MainNavigationMenuComponent.MainNavMenuChild.Profile
}

@Composable
fun MainNavMenuScreen(component: MainNavigationMenuComponent) {
    //val userData = component.profileViewModel.getUserData()

    val stack by component.pages.subscribeAsState()
    val current = stack.active.instance

    val screens by remember {
        mutableStateOf(
            listOf(
                BottomNavScreensState(
                    id = RootNavigationMenuId.Team,
                    openScreen = { component.onState(NavigationMenuState.OpenTeam) },
                    isSelected = false,
                    title = StringShared.team
                    //icon = SharedRes.images.ic_premium_plans
                ),
                BottomNavScreensState(
                    id = RootNavigationMenuId.History,
                    title = StringShared.history,
                    openScreen = { component.onState(NavigationMenuState.OpenHistory) },
                    isSelected = false,
                    //icon = SharedRes.images.ic_categorize
                ),
                BottomNavScreensState(
                    id = RootNavigationMenuId.Landing,
                    title = StringShared.home,
                    openScreen = { component.onState(NavigationMenuState.OpenLanding) },
                    isSelected = false,
                    //icon = SharedRes.images.ic_home
                ),
                BottomNavScreensState(
                    id = RootNavigationMenuId.Profile,
                    openScreen = { component.onState(NavigationMenuState.OpenProfile) },
                    isSelected = false
                )
            )
        )
    }


    if (isMobileDevice())
        MainBottomBarElement(component = component, currentStack = current, screens = screens)
    else
        TopBarElement(component = component, currentStack = current, screens = screens)

}

@OptIn(InternalResourceApi::class)
@Composable
fun MenuBar(
    modifier: Modifier,
    screen: BottomNavScreensState,
    currentStack: MainNavigationMenuComponent.MainNavMenuChild,
    onScreenSelected: (BottomNavScreensState) -> Unit
) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = {
                    onScreenSelected(screen)
                    screen.openScreen()
                },
            ).height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if (screen.id == RootNavigationMenuId.Profile)
            ProfileBottomNavIcon(
                MenuTintColor(screen, currentStack),
                avatar = ""
            )
        else
            Image(
                painter = painterResource(screen.icon ?: DrawableShared.empty),
                contentDescription = "",
                colorFilter = ColorFilter.tint(
                    MenuTintColor(
                        screen,
                        currentStack
                    )
                )
            )

        // Show the label
        if (screen.title != null)
            Text(
                text = screen.title.asString(),
                color = MenuTintColor(screen, currentStack),
                style = AppTypography().body12
            )
    }

}


@Composable
fun MenuBarContent(component: MainNavigationMenuComponent, innerPadding: PaddingValues) {
    Column(modifier = Modifier.padding(innerPadding)) {
        Children(
            stack = component.pages,
            animation = stackAnimation(fade()),
        ) {
            when (val child = it.instance) {
                is MainNavigationMenuComponent.MainNavMenuChild.History -> HistoryScreen(component = child.component)
                is MainNavigationMenuComponent.MainNavMenuChild.Landing -> LandingScreen(component = child.component)
                is MainNavigationMenuComponent.MainNavMenuChild.Profile -> ProfileScreen(component = child.component)
                is MainNavigationMenuComponent.MainNavMenuChild.Team -> TeamScreen(component = child.component)
            }
        }
    }
}

@Composable
fun ProfileBottomNavIcon(tintColor: Color, avatar: String) {
    Box(
        modifier = Modifier
            .size(52.dp)
            .border(2.dp, tintColor, CircleShape)
            .padding(1.dp)
            .clip(CircleShape)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        KamelImage(
            modifier = Modifier.size(47.dp),
            resource = { asyncPainterResource(avatar) },
            contentDescription = "",
            onLoading = { progress ->
            },
            onFailure = { Image(DrawableShared.profile.asDrawable(), "") })
    }
}

