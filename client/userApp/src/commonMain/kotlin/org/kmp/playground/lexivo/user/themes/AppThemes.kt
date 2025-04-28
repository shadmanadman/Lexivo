package org.kmp.playground.lexivo.user.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.kmp.playground.lexivo.core.resources.FontShared
import org.jetbrains.compose.resources.Font

class MyApplicationColors(
    val primaryColor: Color,
    val secondaryColor: Color,
    val background: Color,
    val accent: Color,
    val yellowF7AD45: Color,
    val hardRedBB3E00: Color,
    val slitGray: Color,
    val freeItem: Color,
    val offColor: Color,
    val white: Color,
    val grayFBFBFB: Color,
    val blue005EEB: Color,
    val redF02D56: Color,
    val yellowFCBA03: Color,
    val whiteABABAB: Color,
    val green1C9445: Color,
    val grayF0F0F0: Color,
    val redDE284E: Color,
    val whiteF6FEFF: Color,
    val black201F20: Color,
    val black000000: Color,
    val grayFAFAFA: Color
)


data class MyApplicationTypography(
    val body12: TextStyle,
    val body13: TextStyle,
    val body14: TextStyle,
    val title15: TextStyle,
    val title16: TextStyle,
    val title17: TextStyle,
    val title18: TextStyle,
    val headline19: TextStyle,
    val headline20: TextStyle,
    val headline21: TextStyle,
    val headline22: TextStyle,
    val headline23: TextStyle,
    val headline26: TextStyle

)

@Composable
fun AppTypography(): MyApplicationTypography {
    val appFontRegular = FontFamily(Font(FontShared.ralewayRegular, weight = FontWeight.Normal))

    return MyApplicationTypography(
        body12 = TextStyle(
            fontFamily = appFontRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        body13 = TextStyle(
            fontFamily = appFontRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp
        ),
        body14 = TextStyle(
            fontFamily = appFontRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        title15 = TextStyle(
            fontFamily = appFontRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp
        ),
        title16 = TextStyle(
            fontFamily = appFontRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        title17 = TextStyle(
            fontFamily = appFontRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        ),
        title18 = TextStyle(
            fontFamily = appFontRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        ),
        headline19 = TextStyle(
            fontFamily = appFontRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 19.sp
        ),
        headline20 = TextStyle(
            fontFamily = appFontRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        ),
        headline21 = TextStyle(
            fontFamily = appFontRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 21.sp
        ),
        headline22 = TextStyle(
            fontFamily = appFontRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp
        ),
        headline23 = TextStyle(
            fontFamily = appFontRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 23.sp
        ),
        headline26 = TextStyle(
            fontFamily = appFontRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 26.sp
        )
    )
}

/**
 * This is the definition of current app colors:
 * @return An instance of [MyApplicationColors]
 *
 * In White Mode:
 * [MyApplicationColors.primaryColor] is #FFA500
 * [MyApplicationColors.secondaryColor] is #6E6E6E
 * [MyApplicationColors.background] is #F6FEFF
 * [MyApplicationColors.yellowF7AD45] is #201F20
 * [MyApplicationColors.hardRedBB3E00] is #6E6E6E
 * [MyApplicationColors.slitGray] is #D3D3D3
 */
@Composable
fun AppColors(darkTheme: Boolean = isSystemInDarkTheme()): MyApplicationColors {
    return if (darkTheme) {
        MyApplicationColors(
            primaryColor = Color(0xFFA55B4B),
            secondaryColor = Color(0xFF4F1C51),
            background = Color(0xFF210F37),
            accent = Color(0xFFDCA06D),
            yellowF7AD45 = Color(0xFF201F20),
            hardRedBB3E00 = Color(0xFF6E6E6E),
            slitGray = Color(0xFFD3D3D3),
            freeItem = Color(0xFF1C9445),
            offColor = Color(0xFFFF0000),
            white = Color(0xFFFFFFFF),
            grayFBFBFB = Color(0xFFFBFBFB),
            blue005EEB = Color(0xFF005EEB),
            redF02D56 = Color(0xFFF02D56),
            yellowFCBA03 = Color(0xFFfcba03),
            whiteABABAB = Color(0xFFABABAB),
            green1C9445 = Color(0xFF1C9445),
            grayF0F0F0 = Color(0xFFF0F0F0),
            redDE284E = Color(0xFFDE284E),
            whiteF6FEFF = Color(0xFFF6FEFF),
            black201F20 = Color(0xFF201F20),
            grayFAFAFA = Color(0xFFFAFAFA),
            black000000 = Color(0xFF000000)
        )
    } else {
        MyApplicationColors(
            primaryColor = Color(0xFFA55B4B),
            secondaryColor = Color(0xFF4F1C51),
            background = Color(0xFF210F37),
            accent = Color(0xFFDCA06D),
            yellowF7AD45 = Color(0xFF201F20),
            hardRedBB3E00 = Color(0xFF6E6E6E),
            slitGray = Color(0xFFD3D3D3),
            freeItem = Color(0xFF1C9445),
            offColor = Color(0xFFFF0000),
            white = Color(0xFFFFFFFF),
            grayFBFBFB = Color(0xFFFBFBFB),
            blue005EEB = Color(0xFF005EEB),
            redF02D56 = Color(0xFFF02D56),
            yellowFCBA03 = Color(0xFFfcba03),
            whiteABABAB = Color(0xFFABABAB),
            green1C9445 = Color(0xFF1C9445),
            grayF0F0F0 = Color(0xFFF0F0F0),
            redDE284E = Color(0xFFDE284E),
            whiteF6FEFF = Color(0xFFF6FEFF),
            black201F20 = Color(0xFF201F20),
            grayFAFAFA = Color(0xFFFAFAFA),
            black000000 = Color(0xFF000000)
        )
    }
}