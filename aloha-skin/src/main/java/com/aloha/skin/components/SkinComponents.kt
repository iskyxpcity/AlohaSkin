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

/** Reusable outlined frame for grouping related controls. */
@Composable
fun SkinFrame(
    modifier: Modifier = Modifier,
    title: String? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = .75f)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            if (title != null) {
                Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(10.dp))
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
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = .45f)),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column {
            Row(
                Modifier.fillMaxWidth().clickable { onExpandedChange(!expanded) }.padding(horizontal = 18.dp, vertical = 17.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(title, modifier = Modifier.weight(1f), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                Text("⌄", modifier = Modifier.rotate(rotation), color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            if (expanded) {
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = .35f))
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) { content() }
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
            .padding(vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        if (leading != null) leading()
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
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
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        choices.forEach { choice ->
            val isSelected = choice == selected
            Card(
                modifier = Modifier.then(if (enabled) Modifier.clickable { onSelected(choice) } else Modifier),
                shape = CircleShape,
                border = BorderStroke(1.dp, (if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline).copy(alpha = if (enabled) 1f else .38f)),
                colors = CardDefaults.cardColors(containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Text(
                    choice,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp),
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
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(title, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Text(value.toInt().toString(), color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.labelMedium)
        }
        Slider(value = value, onValueChange = onValueChange, valueRange = valueRange)
    }
}
