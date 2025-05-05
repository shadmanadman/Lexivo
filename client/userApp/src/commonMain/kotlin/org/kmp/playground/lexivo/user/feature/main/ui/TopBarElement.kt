package org.kmp.playground.lexivo.user.feature.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.kmp.playground.lexivo.user.feature.main.MainNavigationMenuComponent
import org.kmp.playground.lexivo.user.themes.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarElement(
    component: MainNavigationMenuComponent,
    currentStack: MainNavigationMenuComponent.MainNavMenuChild,
    screens: List<BottomNavScreensState>
) {

    var selectedItem by remember { mutableStateOf(RootNavigationMenuId.Landing) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppColors().background,
        topBar = {
            TopAppBar(title = {}, actions = {
                screens.forEach { screen ->
                    MenuBar(Modifier.weight(1f),screen, currentStack, onScreenSelected = {
                        selectedItem = it.id
                    })
                }
            })
        },
        content = { innerPadding ->
            MenuBarContent(component, innerPadding)
        })
}