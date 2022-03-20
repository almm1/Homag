package com.example.homag.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ), h1 = TextStyle( //заголовок
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W900,
        fontSize = 32.sp,
        color = textColor,
        letterSpacing = 2.sp
    ), subtitle1 = TextStyle( //
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color.Black
    ), subtitle2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        color = semiWhiteTextColor,
        textAlign = TextAlign.Center
    ), button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
    ), body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
        textAlign = TextAlign.Start
    ),
    h3 = TextStyle(
        //labelTextField
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        color = semiWhiteTextColor,
    ),
    h4 = TextStyle(
        //titleTextField
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 18.sp,
        color = placeholderTextColor,
    ), h5 = TextStyle()
)

