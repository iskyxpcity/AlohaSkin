package com.aloha.skin.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aloha.skin.theme.SkinElevation
import com.aloha.skin.theme.SkinRadii
import com.aloha.skin.theme.SkinSpacing
import com.aloha.skin.theme.SkinStroke

/** Reusable outlined frame for grouping related controls. */
@Composable
fun SkinFrame(
    modifier: Modifier = Modifier,
    title: String? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(SkinRadii.frame),
        border = BorderStroke(SkinStroke.hairline, MaterialTheme.colorScheme.outline.copy(alpha = .75f)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = SkinElevation.flat)
    ) {
        Column(Modifier.padding(SkinSpacing.lg)) {
            if (title != null) {
                Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(SkinSpacing.sm + SkinSpacing.xs))
            }
            content()
        }
    }
}

/** Expand/collapse visual pattern only; caller owns any state or business behavior. */
@Composable
fun SkinAccordion(
    title: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val rotation by animateFloatAsState(if (expanded) 180f else 0f, label = "accordion-arrow")
    Card(
        modifier = modifier.fillMaxWidth().animateContentSize(),
        shape = RoundedCornerShape(SkinRadii.frame),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(SkinStroke.hairline, MaterialTheme.colorScheme.outline.copy(alpha = .45f)),
        elevation = CardDefaults.cardElevation(SkinElevation.flat)
    ) {
        Column {
            Row(
                Modifier.fillMaxWidth().clickable { onExpandedChange(!expanded) }.padding(horizontal = SkinSpacing.lg + SkinSpacing.xs, vertical = SkinSpacing.lg + SkinSpacing.xs),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(title, modifier = Modifier.weight(1f), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                Text("⌄", modifier = Modifier.rotate(rotation), color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            if (expanded) {
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = .35f))
                Column(Modifier.padding(SkinSpacing.lg), verticalArrangement = Arrangement.spacedBy(SkinSpacing.md + SkinSpacing.xs)) { content() }
            }
        }
    }
}

/** Preference-style row with optional supporting text and trailing slot. */
@Composable
fun SkinPreferenceRow(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(vertical = SkinSpacing.sm + SkinSpacing.xs),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SkinSpacing.md + SkinSpacing.xs)
    ) {
        if (leading != null) leading()
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(SkinSpacing.xxs + SkinSpacing.xs / 2)) {
            Text(title, style = MaterialTheme.typography.bodyLarge)
            if (subtitle != null) Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        if (trailing != null) trailing()
    }
}

@Composable
fun SkinSwitchRow(
    title: String,
    subtitle: String? = null,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) = SkinPreferenceRow(title, subtitle, trailing = {
    Switch(checked = checked, onCheckedChange = onCheckedChange)
})

@Composable
fun SkinActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    enabled: Boolean = true
) {
    if (selected) {
        Button(onClick = onClick, enabled = enabled, modifier = modifier) { Text(text) }
    } else {
        TextButton(onClick = onClick, enabled = enabled, modifier = modifier) { Text(text) }
    }
}

@Composable
fun SkinChoiceChips(
    choices: List<String>,
    selected: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(SkinSpacing.sm), verticalAlignment = Alignment.CenterVertically) {
        choices.forEach { choice ->
            val isSelected = choice == selected
            Card(
                modifier = Modifier.then(if (enabled) Modifier.clickable { onSelected(choice) } else Modifier),
                shape = CircleShape,
                border = BorderStroke(SkinStroke.hairline, (if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline).copy(alpha = if (enabled) 1f else .38f)),
                colors = CardDefaults.cardColors(containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent),
                elevation = CardDefaults.cardElevation(SkinElevation.flat)
            ) {
                Text(
                    choice,
                    modifier = Modifier.padding(horizontal = SkinSpacing.lg - SkinSpacing.xs, vertical = SkinSpacing.sm + SkinSpacing.xs / 4),
                    color = (if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface).copy(alpha = if (enabled) 1f else .38f),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
fun SkinSliderRow(
    title: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f
) {
    Column(verticalArrangement = Arrangement.spacedBy(SkinSpacing.xs)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(title, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Text(value.toInt().toString(), color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.labelMedium)
        }
        Slider(value = value, onValueChange = onValueChange, valueRange = valueRange)
    }
}
