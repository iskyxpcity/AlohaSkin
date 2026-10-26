package com.aloha.skin.theme

import android.app.UiModeManager
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
import com.materialkolor.PaletteStyle
import com.materialkolor.rememberDynamicColorScheme

/** User-selectable appearance mode. This is presentation state, not persisted by the library. */
enum class SkinThemeMode {
    SYSTEM, LIGHT, DARK
}

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
 * Shared theme entry point.
 *
 * Precedence: explicit legacy [darkTheme] override, then [themeMode]; system colors on Android
 * 12+ when [dynamicColor] is enabled; MaterialKolor seed palette when [seedColor] is provided;
 * otherwise the provisional static fallback palette.
 *
 * Android 14+ system contrast is passed to MaterialKolor when [contrastLevel] is null.
 * This module intentionally does not persist settings or invoke Voyage business behavior.
 */
@Composable
fun AlohaSkinTheme(
    darkTheme: Boolean? = null,
    dynamicColor: Boolean = false,
    themeMode: SkinThemeMode = SkinThemeMode.SYSTEM,
    seedColor: Color? = null,
    paletteStyle: PaletteStyle = PaletteStyle.TonalSpot,
    contrastLevel: Double? = null,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val systemDark = isSystemInDarkTheme()
    val useDarkTheme = darkTheme ?: when (themeMode) {
        SkinThemeMode.SYSTEM -> systemDark
        SkinThemeMode.LIGHT -> false
        SkinThemeMode.DARK -> true
    }
    val systemContrast = if (Build.VERSION.SDK_INT >= 34) {
        runCatching {
            context.getSystemService(UiModeManager::class.java)?.contrast?.toDouble()
        }.getOrNull()
    } else {
        null
    }
    val resolvedContrast = (contrastLevel ?: systemContrast ?: 0.0).coerceIn(-1.0, 1.0)

    val scheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && useDarkTheme ->
            dynamicDarkColorScheme(context)
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !useDarkTheme ->
            dynamicLightColorScheme(context)
        seedColor != null -> rememberDynamicColorScheme(
            seedColor = seedColor,
            isDark = useDarkTheme,
            style = paletteStyle,
            contrastLevel = resolvedContrast
        )
        useDarkTheme -> darkColorScheme(
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
