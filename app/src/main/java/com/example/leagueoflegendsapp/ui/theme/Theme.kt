package com.example.leagueoflegendsapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val LightColorTheme = lightColorScheme(
    background = Background
)

val DarkColorTheme = lightColorScheme(
    background = BackgroundDark
)

@Composable
fun extendedColor(light: Color, dark: Color): Color {
    return if (isSystemInDarkTheme()) dark else light
}

val ColorScheme.extraColor: Color
    @Composable get() = extendedColor(Color.White, Color.Black)

val shapes = Shapes(extraSmall = RoundedCornerShape(5.dp), medium = RoundedCornerShape(15.dp))

@Composable
fun LeagueOfLegendsAppTheme(content: @Composable () -> Unit) {
    val theme = if (isSystemInDarkTheme()) {
        DarkColorTheme
    } else {
        LightColorTheme
    }
    MaterialTheme(colorScheme = theme, content = content, typography = Typography, shapes = shapes)
}