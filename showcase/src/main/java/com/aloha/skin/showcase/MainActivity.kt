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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aloha.skin.components.SkinAccordion
import com.aloha.skin.components.SkinChoiceChips
import com.aloha.skin.components.SkinFrame
import com.aloha.skin.components.SkinPreferenceRow
import com.aloha.skin.components.SkinSliderRow
import com.aloha.skin.components.SkinSwitchRow
import com.aloha.skin.theme.AlohaSkinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlohaSkinTheme {
                var darkPreview by remember { mutableStateOf(false) }
                var selectedMode by remember { mutableStateOf("System") }
                var enabled by remember { mutableStateOf(true) }
                var radius by remember { mutableFloatStateOf(24f) }
                AlohaSkinTheme(darkTheme = darkPreview) {
                    Column(
                        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text("AlohaSkin", style = MaterialTheme.typography.headlineMedium)
                        Text("Visual component showcase · demo state only", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        SkinFrame(title = "Appearance") {
                            SkinPreferenceRow("Theme preview", "Switch the showcase palette", trailing = {
                                androidx.compose.material3.Switch(darkPreview, { darkPreview = it })
                            })
                            SkinChoiceChips(listOf("Light", "Dark", "System"), selectedMode, {
                                selectedMode = it
                                darkPreview = it == "Dark"
                            }, Modifier.fillMaxWidth())
                        }
                        SkinAccordion("DESIGN", expanded = true, onExpandedChange = {}) {
                            SkinPreferenceRow("Dynamic color", "Use an accent palette", trailing = {
                                androidx.compose.material3.Switch(enabled, { enabled = it })
                            })
                            SkinChoiceChips(listOf("Tonal", "Expressive", "Neutral"), "Expressive", {})
                            SkinSliderRow("Corner size", radius, { radius = it }, 0f..40f)
                        }
                        SkinAccordion("COMPONENTS", expanded = true, onExpandedChange = {}) {
                            SkinSwitchRow("Haptic sample", "Visual sample control; no device feature is wired", enabled, { enabled = it })
                            SkinPreferenceRow("Preference row", "Supporting text and trailing action", trailing = {
                                Text("›", style = MaterialTheme.typography.titleLarge)
                            })
                        }
                        SkinFrame(title = "Surface / frame sample") {
                            Text("Container fill, outline, inset spacing and typography roles.")
                        }
                        Spacer(Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}
