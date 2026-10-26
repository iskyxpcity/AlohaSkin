package com.aloha.skin.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

/** Static fallback palette. Values remain provisional until calibrated against Voyage references. */
object SkinColors {
    val LightBackground = Color(0xFFF7F7FA)
    val LightSurface = Color(0xFFFFFFFF)
    val LightSurfaceVariant = Color(0xFFF0F0F5)
    val LightText = Color(0xFF191A20)
    val LightSubtleText = Color(0xFF686A73)
    val LightAccent = Color(0xFF6750A4)
    val LightOutline = Color(0xFFDADAE2)
    val DarkBackground = Color(0xFF111116)
    val DarkSurface = Color(0xFF1B1B22)
    val DarkSurfaceVariant = Color(0xFF262630)
    val DarkText = Color(0xFFF0EFF7)
    val DarkSubtleText = Color(0xFFB8B6C3)
    val DarkAccent = Color(0xFFD0BCFF)
    val DarkOutline = Color(0xFF45444F)
}

/**
 * Applies AlohaSkin's semantic Material 3 theme.
 *
 * [dynamicColor] opts into Android's system dynamic palette on Android 12+.
 * This is theme plumbing only; it does not persist user choices or import Voyage logic.
 * A custom seed/palette generator is intentionally separate and remains future work.
 * Shared shape tokens are applied here; their values remain provisional until measured.
 */
@Composable
fun AlohaSkinTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val scheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && darkTheme ->
            dynamicDarkColorScheme(context)
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !darkTheme ->
            dynamicLightColorScheme(context)
        darkTheme -> darkColorScheme(
            primary = SkinColors.DarkAccent,
            onPrimary = Color(0xFF30204F),
            background = SkinColors.DarkBackground,
            onBackground = SkinColors.DarkText,
            surface = SkinColors.DarkSurface,
            onSurface = SkinColors.DarkText,
            surfaceVariant = SkinColors.DarkSurfaceVariant,
            onSurfaceVariant = SkinColors.DarkSubtleText,
            outline = SkinColors.DarkOutline
        )
        else -> lightColorScheme(
            primary = SkinColors.LightAccent,
            onPrimary = Color.White,
            background = SkinColors.LightBackground,
            onBackground = SkinColors.LightText,
            surface = SkinColors.LightSurface,
            onSurface = SkinColors.LightText,
            surfaceVariant = SkinColors.LightSurfaceVariant,
            onSurfaceVariant = SkinColors.LightSubtleText,
            outline = SkinColors.LightOutline
        )
    }
    MaterialTheme(colorScheme = scheme, shapes = SkinShapes, content = content)
}
