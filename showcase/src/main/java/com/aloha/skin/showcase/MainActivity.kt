package com.aloha.skin.showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aloha.skin.components.SkinAccordion
import com.aloha.skin.components.SkinButtonPair
import com.aloha.skin.components.SkinChoiceChips
import com.aloha.skin.components.SkinFrame
import com.aloha.skin.components.SkinPaletteCard
import com.aloha.skin.components.SkinPreferenceRow
import com.aloha.skin.components.SkinSliderRow
import com.aloha.skin.components.SkinSwitchRow
import com.aloha.skin.components.SkinTabs
import com.aloha.skin.theme.AlohaSkinTheme
import com.aloha.skin.theme.SkinColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkPreview by remember { mutableStateOf(false) }
            AlohaSkinTheme(darkTheme = darkPreview) {
                var selectedMode by remember { mutableStateOf("System") }
                var enabled by remember { mutableStateOf(true) }
                var radius by remember { mutableFloatStateOf(24f) }
                var selectedTab by remember { mutableIntStateOf(0) }
                var selectedPalette by remember { mutableIntStateOf(0) }
                Column(
                    Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("AlohaSkin", style = MaterialTheme.typography.headlineMedium)
                    Text("Visual component showcase · demo state only", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    SkinFrame(title = "Appearance") {
                        SkinPreferenceRow("Dark preview", "Toggle the demo color scheme", trailing = {
                            androidx.compose.material3.Switch(darkPreview, { darkPreview = it })
                        })
                        SkinChoiceChips(listOf("Light", "Dark", "System"), selectedMode, {
                            selectedMode = it
                            darkPreview = it == "Dark"
                        }, Modifier.fillMaxWidth())
                    }
                    SkinFrame(title = "Tabs") {
                        SkinTabs(listOf("Components", "Surfaces", "Palettes"), selectedTab, { selectedTab = it })
                        Text("Selected tab: ${listOf("Components", "Surfaces", "Palettes")[selectedTab]}", modifier = Modifier.padding(top = 12.dp))
                    }
                    SkinAccordion("DESIGN", expanded = true, onExpandedChange = {}) {
                        SkinPreferenceRow("Dynamic color sample", "Local preview only", trailing = {
                            androidx.compose.material3.Switch(enabled, { enabled = it })
                        })
                        SkinChoiceChips(listOf("Tonal", "Expressive", "Neutral"), "Expressive", {})
                        SkinSliderRow("Corner size", radius, { radius = it }, 0f..40f)
                    }
                    SkinAccordion("COMPONENTS", expanded = true, onExpandedChange = {}) {
                        SkinSwitchRow("Sample switch", "No device feature is wired", enabled, { enabled = it })
                        SkinPreferenceRow("Preference row", "Supporting text and trailing action", trailing = {
                            Text("›", style = MaterialTheme.typography.titleLarge)
                        })
                        SkinButtonPair("Primary", "Outlined", {}, {}, enabled = enabled)
                    }
                    SkinFrame(title = "Theme palette previews") {
                        SkinPaletteCard("Violet", listOf(SkinColors.LightAccent, Color(0xFF8A80B8), Color(0xFFB9B0D8), Color(0xFFE4DFF2)), selectedPalette == 0, { selectedPalette = 0 }, Modifier.fillMaxWidth())
                        Spacer(Modifier.height(10.dp))
                        SkinPaletteCard("Ocean", listOf(Color(0xFF245B78), Color(0xFF4D879F), Color(0xFF8AB6C5), Color(0xFFD7E8ED)), selectedPalette == 1, { selectedPalette = 1 }, Modifier.fillMaxWidth())
                    }
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}
