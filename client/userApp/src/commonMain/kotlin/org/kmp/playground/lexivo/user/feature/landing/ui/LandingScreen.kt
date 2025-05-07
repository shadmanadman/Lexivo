package org.kmp.playground.lexivo.user.feature.landing.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.playground.lexivo.core.resources.StringShared
import org.kmp.playground.lexivo.core.resources.asString
import org.kmp.playground.lexivo.user.feature.landing.LandingComponent
import org.kmp.playground.lexivo.user.themes.AppColors
import org.kmp.playground.lexivo.user.themes.AppTypography

@Composable
fun LandingScreen(component: LandingComponent) {

    LandingPreview()
}

@Preview()
@Composable
fun LandingPreview() {
    var inputMessage by remember { mutableStateOf(TextFieldValue("")) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(AppColors().background).fillMaxSize()) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            textAlign = TextAlign.Center,
            text = stringResource(StringShared.Lexivo_Your_AI_Partner_for_Perfecting_Every_Message),
            color = AppColors().white,
            style = AppTypography().title18.copy(fontWeight = FontWeight.Bold)
        )

        OutlinedTextField(
            modifier = Modifier.padding(16.dp).fillMaxWidth().defaultMinSize(minHeight = 200.dp),
            shape = RoundedCornerShape(18.dp),
            value = inputMessage,
            onValueChange = { inputText ->
                inputMessage = inputText
            },
            label = {
                Text(text = "Put your message, text, email")
            })
        TuningOptions()

    }
}

data class TuningOption(val id: Int, val text: String, val icon: String)

@Composable
fun TuningOptions() {
    val listOfTuningOptions = listOf(
        TuningOption(id = 1, text = StringShared.funny_tune.asString(), icon = "tone"),
        TuningOption(id = 2, text = StringShared.professional_tune.asString(), icon = "tone"),
        TuningOption(id = 3, text = StringShared.happy_tune.asString(), icon = "tone"),
        TuningOption(id = 4, text = StringShared.angry_tune.asString(), icon = "tone")
    )
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOfTuningOptions.forEach { tuningOption ->
            TuningButton(text = tuningOption.text, onClick = {})
        }
    }
}

@Composable
fun TuningButton(text: String, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = AppColors().black201F20),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.size(80.dp, 60.dp)
            .border(1.dp, AppColors().white, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Text(text = text, fontSize = TextUnit.Unspecified)
    }
}
