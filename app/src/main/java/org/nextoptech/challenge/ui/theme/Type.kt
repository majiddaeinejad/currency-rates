package org.nextoptech.challenge.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.nextoptech.challenge.R

// Set of Material typography styles to start with
val satoshiFamily = FontFamily(
    Font(resId = R.font.satoshi_regular, weight = FontWeight.Normal),
    Font(resId = R.font.satoshi_medium, weight = FontWeight.Medium),
    Font(resId = R.font.satoshi_bold, weight = FontWeight.Bold),
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 27.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        lineHeight = 37.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)