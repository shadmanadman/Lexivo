package org.kmp.playground.lexivo.user.feature.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.kmp.playground.lexivo.core.resources.DrawableShared
import org.kmp.playground.lexivo.user.feature.auth.AuthComponent
import org.kmp.playground.lexivo.user.feature.landing.ui.GradiantBackground
import org.kmp.playground.lexivo.user.themes.AppColors
import org.kmp.playground.lexivo.user.themes.AppTypography

@Composable
fun LoginScreen(component: AuthComponent) {
    Box(modifier = Modifier.fillMaxSize()) {
        GradiantBackground()
        Box(modifier = Modifier.fillMaxWidth().height(300.dp).align(Alignment.Center).background(
            AppColors().background, shape = RoundedCornerShape(12.dp)
        ).clip(RoundedCornerShape(12.dp))) {

        }
    }
}

@Composable
fun AuthWithEmail() {
    var email by remember { mutableStateOf(TextFieldValue("")) }

    Column {
        OutlinedTextField(
            modifier = Modifier.padding(top = 16.dp, start = 60.dp, end = 60.dp).fillMaxWidth()
                .defaultMinSize(minHeight = 200.dp),
            shape = RoundedCornerShape(18.dp),
            value = email,
            onValueChange = { inputText ->
                email = inputText
            },
            label = {
                Text(text = "Email or username", color = Color.White)
            })

        OutlinedTextField(
            modifier = Modifier.padding(top = 16.dp, start = 60.dp, end = 60.dp).fillMaxWidth()
                .defaultMinSize(minHeight = 200.dp),
            shape = RoundedCornerShape(18.dp),
            value = email,
            onValueChange = { inputText ->
                email = inputText
            },
            label = {
                Text(text = "Password", color = Color.White)
            })


    }
}


@Composable
fun AuthWithProviders() {
    Box(
        modifier = Modifier.fillMaxWidth().height(50.dp).clip(RoundedCornerShape(12.dp))
            .padding(start = 24.dp, end = 24.dp)
    ) {
        Row {
            Image(painter = painterResource(DrawableShared.ic_google), contentDescription = "")
            Text("SignIn with Google", style = AppTypography().title18)
        }
    }
}