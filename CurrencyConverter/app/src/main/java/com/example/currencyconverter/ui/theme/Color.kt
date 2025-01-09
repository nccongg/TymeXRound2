package com.example.currencyconverter.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Pink = Color(0xFFF28482)
val Green = Color(0xFF84A59D)
val Yellow = Color(0xFFF7EDE2)
val YellowLight = Color(0xFFFFFFF2)
val Dark = Color(0xFF3D405B)
val Primary = Color(0xFF6200EE)
val PrimaryVariant = Color(0xFF3700B3)
val Secondary = Color(0xFF03DAC6)
val SecondaryVariant = Color(0xFF018786)
val Error = Color(0xFFB00020)
val White = Color(0xFFFFFFFF)

@Immutable
data class AppColors(
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val secondarySurface: Color,
    val onSecondarySurface: Color,
    val regularSurface: Color,
    val onRegularSurface: Color,
    val actionSurface: Color,
    val onActionSurface: Color,
    val highlightSurface: Color,
    val onHighlightSurface: Color,
    val primary: Color,
    val onPrimary: Color,
    val primaryVariant: Color,
    val onPrimaryVariant: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryVariant: Color,
    val onSecondaryVariant: Color,
    val error: Color,
    val onError: Color,
    val white: Color
)

val LocalAppColors = staticCompositionLocalOf {
    AppColors(
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        surface = Color.Unspecified,
        onSurface = Color.Unspecified,
        secondarySurface = Color.Unspecified,
        onSecondarySurface = Color.Unspecified,
        regularSurface = Color.Unspecified,
        onRegularSurface = Color.Unspecified,
        actionSurface = Color.Unspecified,
        onActionSurface = Color.Unspecified,
        highlightSurface = Color.Unspecified,
        onHighlightSurface = Color.Unspecified,
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        primaryVariant = Color.Unspecified,
        onPrimaryVariant = Color.Unspecified,
        secondary = Color.Unspecified,
        onSecondary = Color.Unspecified,
        secondaryVariant = Color.Unspecified,
        onSecondaryVariant = Color.Unspecified,
        error = Color.Unspecified,
        onError = Color.Unspecified,
        white = Color.Unspecified,
    )
}

val extendedColor = AppColors(
    background = Color.White,
    onBackground = Dark,
    surface = Color.White,
    onSurface = Dark,
    secondarySurface = Pink,
    onSecondarySurface = Color.White,
    regularSurface = YellowLight,
    onRegularSurface = Dark,
    actionSurface = Yellow,
    onActionSurface = Pink,
    highlightSurface = Green,
    onHighlightSurface = Color.White,
    primary = Primary,
    onPrimary = Color.White,
    primaryVariant = PrimaryVariant,
    onPrimaryVariant = Color.White,
    secondary = Secondary,
    onSecondary = Dark,
    secondaryVariant = SecondaryVariant,
    onSecondaryVariant = Color.White,
    error = Error,
    onError = Color.White,
    white = White,
)
