package com.example.leagueoflegendsapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography: Typography
    @Composable get() = Typography(
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal, fontSize = 17.sp, lineHeight = 24.sp
        ), bodyMedium = TextStyle(
            fontWeight = FontWeight.Medium, fontSize = 15.sp, lineHeight = 20.sp
        ), bodySmall = TextStyle(
            fontWeight = FontWeight.Normal, fontSize = 15.sp, lineHeight = 24.sp
        ), titleLarge = TextStyle(
            fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 36.sp
        ), titleSmall = TextStyle(
            fontWeight = FontWeight.Medium, fontSize = 17.sp, lineHeight = 24.sp
        )
    )