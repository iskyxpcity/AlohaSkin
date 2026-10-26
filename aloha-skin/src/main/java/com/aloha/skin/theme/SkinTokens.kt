package com.aloha.skin.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Shared visual tokens for the standalone skin library.
 *
 * These values centralize the current scaffold; they are provisional until measured
 * against verified Voyage screenshots/source styles. Keep them here rather than
 * duplicating hard-coded dimensions across components.
 */
object SkinSpacing {
    val xxs: Dp = 2.dp
    val xs: Dp = 4.dp
    val sm: Dp = 8.dp
    val md: Dp = 12.dp
    val lg: Dp = 16.dp
    val xl: Dp = 20.dp
    val xxl: Dp = 24.dp
}

object SkinRadii {
    val control: Dp = 12.dp
    val card: Dp = 18.dp
    val frame: Dp = 24.dp
    val sheetTop: Dp = 28.dp
}

object SkinStroke {
    val hairline: Dp = 1.dp
    val selected: Dp = 2.dp
}

object SkinElevation {
    val flat: Dp = 0.dp
    val raised: Dp = 2.dp
    val floating: Dp = 6.dp
}

/** Material 3 shape scale backed by the shared provisional radius tokens. */
val SkinShapes = Shapes(
    extraSmall = RoundedCornerShape(SkinSpacing.xs),
    small = RoundedCornerShape(SkinRadii.control),
    medium = RoundedCornerShape(SkinRadii.card),
    large = RoundedCornerShape(SkinRadii.frame),
    extraLarge = RoundedCornerShape(SkinRadii.sheetTop)
)
