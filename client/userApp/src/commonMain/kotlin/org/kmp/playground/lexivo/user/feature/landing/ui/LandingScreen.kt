package org.kmp.playground.lexivo.user.feature.landing.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.playground.lexivo.core.resources.StringShared
import org.kmp.playground.lexivo.user.feature.landing.LandingComponent
import org.kmp.playground.lexivo.user.themes.AppColors
import org.kmp.playground.lexivo.user.themes.AppTypography

@Composable
fun LandingScreen(component: LandingComponent) {

}

@Preview()
@Composable
fun LandingPreview() {
    Column(modifier = Modifier.background(AppColors().background).fillMaxSize()) {
        Text(
            text = stringResource(StringShared.Lexivo_Your_AI_Partner_for_Perfecting_Every_Message),
            color = AppColors().secondaryColor,
            style = AppTypography().title17.copy(fontWeight = FontWeight.Bold)
        )
    }
}