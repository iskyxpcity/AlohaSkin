package com.aloha.skin.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** A visual navigation item. The host owns destinations and all navigation behavior. */
data class SkinNavigationItem(
    val label: String,
    val selected: Boolean,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit
)

/**
 * Reusable floating navigation surface with optional translucent/glass-like treatment.
 * This is a visual primitive only; it intentionally has no routing or persistence.
 *
 * Glass opacity, radius and elevation are provisional tokens until compared to Voyage
 * reference screenshots. Callers can disable the translucent treatment.
 */
@Composable
fun SkinFloatingNavigationBar(
    items: List<SkinNavigationItem>,
    modifier: Modifier = Modifier,
    glassStyle: Boolean = true
) {
    if (items.isEmpty()) return
    val colors = MaterialTheme.colorScheme
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        color = colors.surface.copy(alpha = if (glassStyle) 0.88f else 1f),
        contentColor = colors.onSurface,
        border = BorderStroke(1.dp, colors.outline.copy(alpha = if (glassStyle) 0.42f else 0.7f)),
        tonalElevation = if (glassStyle) 3.dp else 0.dp,
        shadowElevation = if (glassStyle) 4.dp else 0.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 7.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val iconContainer by animateColorAsState(
                    targetValue = if (item.selected) colors.secondaryContainer else Color.Transparent,
                    label = "navigation-item-container"
                )
                val foreground by animateColorAsState(
                    targetValue = if (item.selected) colors.onSecondaryContainer else colors.onSurfaceVariant,
                    label = "navigation-item-foreground"
                )
                Column(
                    modifier = Modifier.weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable(onClick = item.onClick)
                        .padding(vertical = 3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Box(
                        modifier = Modifier.size(width = 56.dp, height = 30.dp)
                            .clip(CircleShape)
                            .then(Modifier),
                        contentAlignment = Alignment.Center
                    ) {
                        Surface(
                            modifier = Modifier.matchParentSize(),
                            color = iconContainer,
                            shape = CircleShape
                        ) {}
                        androidx.compose.runtime.CompositionLocalProvider(
                            androidx.compose.material3.LocalContentColor provides foreground
                        ) {
                            item.icon()
                        }
                    }
                    Text(
                        text = item.label,
                        color = foreground,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (item.selected) FontWeight.SemiBold else FontWeight.Normal,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
