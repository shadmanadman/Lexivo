package org.kmp.playground.lexivo.user.feature.landing.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.websocket.Frame
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.playground.lexivo.core.resources.DrawableShared
import org.kmp.playground.lexivo.core.resources.StringShared
import org.kmp.playground.lexivo.core.resources.asDrawable
import org.kmp.playground.lexivo.core.resources.asString
import org.kmp.playground.lexivo.user.feature.landing.LandingComponent
import org.kmp.playground.lexivo.user.platform.isMobileDevice
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

    Box(modifier = Modifier.background(AppColors().background).fillMaxSize()) {
        GradiantBackground()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                textAlign = TextAlign.Center,
                text = stringResource(StringShared.Lexivo_Your_AI_Partner_for_Perfecting_Every_Message),
                color = AppColors().white,
                style = AppTypography().title18.copy(
                    fontSize = landingTitleSize(),
                    fontWeight = FontWeight.Bold
                )
            )

            OutlinedTextField(
                modifier = Modifier.padding(top = 16.dp, start = 60.dp, end = 60.dp).fillMaxWidth()
                    .defaultMinSize(minHeight = 200.dp),
                shape = RoundedCornerShape(18.dp),
                value = inputMessage,
                onValueChange = { inputText ->
                    inputMessage = inputText
                },
                label = {
                    Text(text = "Put your message, text, email", color = Color.White)
                })
            TuningOptions()
            GenerateButton(onClick = {})
            ConvertedText("")
        }
    }
}

@Composable
fun GenerateButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(top = 18.dp)
            .size(width = 180.dp, height = 50.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .background(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                    listOf(Color(0xFF22C1C3), Color(0xFFFDBB2D)), tileMode = TileMode.Clamp
                )
            )
    ) {
        Box(
            modifier = Modifier.padding(start = 8.dp).size(40.dp).clip(CircleShape)
                .border(width = 1.dp, color = AppColors().grayFAFAFA, shape = CircleShape)
                .align(Alignment.CenterStart)
        ) {
            Image(painter = painterResource(DrawableShared.ic_magic), contentDescription = "")
        }
        Text(
            modifier = Modifier.padding(start = 12.dp).align(Alignment.Center),
            text = "Generate",
            color = AppColors().white,
            style = AppTypography().title18
        )
    }
}

@Composable
fun ConvertedText(text: String) {
    Box(
        modifier = Modifier.padding(top = 16.dp, start = 60.dp, end = 60.dp).fillMaxWidth().defaultMinSize(minHeight = 200.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(12.dp))

    ) {
        Text(text = text, color = Color.Black)
    }
}

data class TuningOption(val id: Int, val text: String, val icon: Painter)

@Composable
fun TuningOptions() {
    val listOfTuningOptions = listOf(
        TuningOption(
            id = 1,
            text = StringShared.funny_tune.asString(),
            icon = DrawableShared.ic_funny.asDrawable()
        ),
        TuningOption(
            id = 2,
            text = StringShared.professional_tune.asString(),
            icon = DrawableShared.ic_suitcase.asDrawable()
        ),
        TuningOption(
            id = 3,
            text = StringShared.happy_tune.asString(),
            icon = DrawableShared.ic_happy.asDrawable()
        ),
        TuningOption(
            id = 4,
            text = StringShared.angry_tune.asString(),
            icon = DrawableShared.ic_mad.asDrawable()
        )
    )
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOfTuningOptions.forEach { tuningOption ->
            TuningButton(text = tuningOption.text, icon = tuningOption.icon, onClick = {})
        }
    }
}

@Composable
fun TuningButton(text: String, icon: Painter, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(70.dp), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.size(70.dp),
                painter = painterResource(DrawableShared.mesh_gradiant_yellow_purple),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.size(38.dp),
                painter = icon,
                contentDescription = ""
            )
        }
        Text(text = text, fontSize = TextUnit.Unspecified, color = AppColors().white)
    }
}

@Composable
fun GradiantFlow() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize().rotate(180f),
            painter = painterResource(DrawableShared.mesh_gradiant_background),
            contentDescription = ""
        )
    }
}


@Composable
fun GradiantBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(DrawableShared.landing_background),
            contentDescription = ""
        )
    }
}


fun landingTitleSize(): TextUnit {
    return if (isMobileDevice()) 18.sp else 38.sp
}
