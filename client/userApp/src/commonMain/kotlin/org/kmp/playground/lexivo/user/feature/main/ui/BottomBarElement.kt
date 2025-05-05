package org.kmp.playground.lexivo.user.feature.main.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.kmp.playground.lexivo.user.feature.main.MainNavigationMenuComponent
import org.kmp.playground.lexivo.user.themes.AppColors

@Composable
fun MainBottomBarElement(
    component: MainNavigationMenuComponent,
    currentStack: MainNavigationMenuComponent.MainNavMenuChild,
    screens: List<BottomNavScreensState>
) {

    var selectedItem by remember { mutableStateOf(RootNavigationMenuId.Landing) }

    Scaffold(bottomBar = {
        BottomAppBar(
            modifier = Modifier.fillMaxWidth().height(80.dp)
                .shadow(
                    elevation = 8.dp,
                    spotColor = Color(0x1A000000),
                    ambientColor = Color(0x1A000000)
                ),
            containerColor = AppColors().background,
            actions = {
                screens.forEach { screen ->
                    MenuBar(screen, currentStack, onScreenSelected = {
                        selectedItem = it.id
                    })
                }
            })
    }, content = { innerPadding ->
        MenuBarContent(component, innerPadding)
    })
}



/**
 * Change the tint color for each selector option
 */
@Composable
fun MenuTintColor(
    screensBottom: BottomNavScreensState,
    current: MainNavigationMenuComponent.MainNavMenuChild
): Color {
    return if (screensBottom.id.matchesMainNavMenuChild(current))
        AppColors().accent
    else
        AppColors().slitGray
}