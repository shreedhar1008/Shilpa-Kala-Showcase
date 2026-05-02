package com.shilpakala.showcase.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = WarmGold,
    onPrimary = Color.White,
    secondary = StoneBrown,
    tertiary = MutedSage,
    background = CreamWhite,
    onBackground = DeepCharcoal,
    surface = SoftTaupe,
    onSurface = DeepCharcoal
)

private val DarkColorScheme = darkColorScheme(
    primary = WarmGold,
    onPrimary = Color.White,
    background = DeepEbony,
    onBackground = CreamWhite,
    surface = DeepCharcoal,
    onSurface = CreamWhite
)

@Composable
fun ShilpaKalaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
