package com.aloha.skin.showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aloha.skin.components.SkinAccordion
import com.aloha.skin.components.SkinBottomSheet
import com.aloha.skin.components.SkinButtonPair
import com.aloha.skin.components.SkinChoiceChips
import com.aloha.skin.components.SkinDialog
import com.aloha.skin.components.SkinFloatingNavigationBar
import com.aloha.skin.components.SkinFrame
import com.aloha.skin.components.SkinNavigationItem
import com.aloha.skin.components.SkinPaletteCard
import com.aloha.skin.components.SkinPreferenceRow
import com.aloha.skin.components.SkinSliderRow
import com.aloha.skin.components.SkinSwitchRow
import com.aloha.skin.components.SkinTabs
import com.aloha.skin.theme.AlohaSkinTheme
import com.aloha.skin.theme.SkinColors

private enum class MainPage(val title: String, val icon: String) {
    HOME("主页", "⌂"),
    FEATURES("功能", "◈"),
    STATUS_BAR("状态栏", "▮"),
    SETTINGS("设置", "⚙")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkPreview by remember { mutableStateOf(false) }
            var dynamicPreview by remember { mutableStateOf(false) }
            var selectedPage by remember { mutableStateOf(MainPage.HOME) }
            AlohaSkinTheme(darkTheme = darkPreview, dynamicColor = dynamicPreview) {
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    bottomBar = {
                        SkinFloatingNavigationBar(
                            items = MainPage.values().map { page ->
                                SkinNavigationItem(
                                    label = page.title,
                                    selected = selectedPage == page,
                                    onClick = { selectedPage = page },
                                    icon = { Text(page.icon, style = MaterialTheme.typography.titleMedium) }
                                )
                            },
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                ) { innerPadding ->
                    Column(
                        Modifier.fillMaxSize()
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 20.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        PageHeader(selectedPage)
                        when (selectedPage) {
                            MainPage.HOME -> HomePage(onOpenFeatures = { selectedPage = MainPage.FEATURES }, onOpenSettings = { selectedPage = MainPage.SETTINGS })
                            MainPage.FEATURES -> FeaturesPage()
                            MainPage.STATUS_BAR -> StatusBarPage()
                            MainPage.SETTINGS -> SettingsPage(
                                darkPreview = darkPreview,
                                onDarkPreviewChange = { darkPreview = it },
                                dynamicPreview = dynamicPreview,
                                onDynamicPreviewChange = { dynamicPreview = it }
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun PageHeader(page: MainPage) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(page.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.SemiBold)
        Text(
            when (page) {
                MainPage.HOME -> "AlohaSkin · 视觉主题与组件系统"
                MainPage.FEATURES -> "浏览可复用的视觉组件"
                MainPage.STATUS_BAR -> "状态栏样式预览 · 仅展示，不修改系统"
                MainPage.SETTINGS -> "主题外观与 Showcase 设置"
            },
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun HomePage(onOpenFeatures: () -> Unit, onOpenSettings: () -> Unit) {
    SkinFrame(title = "欢迎使用 AlohaSkin") {
        Text("独立的视觉主题与 UI 组件库", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(6.dp))
        Text(
            "通过四个主导航页面查看主页概览、组件功能、状态栏样式和外观设置。当前页面是可交互的 Showcase，控件不会触发真实系统功能。",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(12.dp))
        SkinButtonPair(
            primaryLabel = "浏览功能",
            secondaryLabel = "外观设置",
            onPrimary = onOpenFeatures,
            onSecondary = onOpenSettings
        )
    }
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        SummaryCard("4", "主导航页面", Modifier.weight(1f))
        SummaryCard("UI", "可复用组件", Modifier.weight(1f))
    }
    SkinFrame(title = "视觉预览") {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            listOf(SkinColors.LightAccent, Color(0xFF8A80B8), Color(0xFFB9B0D8), Color(0xFFE4DFF2)).forEach { color ->
                Box(Modifier.weight(1f).height(48.dp).clip(MaterialTheme.shapes.medium).background(color))
            }
        }
        Spacer(Modifier.height(10.dp))
        Text("颜色仅为当前临时预览值，尚未通过 Voyage 原版截图测量验收。", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun SummaryCard(value: String, label: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun FeaturesPage() {
    var enabled by remember { mutableStateOf(true) }
    var radius by remember { mutableFloatStateOf(24f) }
    var selectedTab by remember { mutableIntStateOf(0) }
    var selectedPalette by remember { mutableIntStateOf(0) }
    var showSheet by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    SkinFrame(title = "组件分类") {
        SkinTabs(listOf("控件", "表面", "配色"), selectedTab, { selectedTab = it })
        Spacer(Modifier.height(12.dp))
        Text(
            listOf("按钮、开关、滑块和选择项", "框体、浮动导航、对话框和底部弹层", "主题色板卡片与选中状态")[selectedTab],
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
    if (selectedTab == 0) {
        SkinAccordion("基础控件", expanded = true, onExpandedChange = {}) {
            SkinSwitchRow("示例控件启用", "关闭后展示禁用状态", enabled, { enabled = it })
            SkinChoiceChips(listOf("Tonal", "Expressive", "Neutral"), "Expressive", {}, enabled = enabled)
            SkinSliderRow("圆角预览值", radius, { radius = it }, 0f..40f)
            SkinButtonPair("打开底部弹层", "打开对话框", { showSheet = true }, { showDialog = true }, enabled = enabled)
        }
    } else if (selectedTab == 1) {
        SkinAccordion("表面与容器", expanded = true, onExpandedChange = {}) {
            Text("下方组件仅展示外观与容器层级。", color = MaterialTheme.colorScheme.onSurfaceVariant)
            SkinButtonPair("显示弹层", "显示对话框", { showSheet = true }, { showDialog = true })
        }
        SkinFrame(title = "浮动导航表面") {
            Text("主导航栏固定在屏幕底部；此处为组件示例说明。")
        }
    } else {
        SkinPaletteCard("Violet", listOf(SkinColors.LightAccent, Color(0xFF8A80B8), Color(0xFFB9B0D8), Color(0xFFE4DFF2)), selectedPalette == 0, { selectedPalette = 0 })
        SkinPaletteCard("Ocean", listOf(Color(0xFF245B78), Color(0xFF4D879F), Color(0xFF8AB6C5), Color(0xFFD7E8ED)), selectedPalette == 1, { selectedPalette = 1 })
    }
    SkinDialog(visible = showDialog, title = "组件对话框", body = "这是一个通用视觉外壳。", onConfirm = { showDialog = false }, onDismiss = { showDialog = false })
    SkinBottomSheet(visible = showSheet, onDismiss = { showSheet = false }) {
        Text("底部弹层示例", style = MaterialTheme.typography.titleLarge)
        Text("内容由宿主传入；不包含业务逻辑。", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun StatusBarPage() {
    var showWifi by remember { mutableStateOf(true) }
    var showMobile by remember { mutableStateOf(true) }
    var showBattery by remember { mutableStateOf(true) }
    var showSpeed by remember { mutableStateOf(true) }
    var showClock by remember { mutableStateOf(true) }
    SkinFrame(title = "状态栏预览") {
        Text("演示区域", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.labelMedium)
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (showClock) Text("9:41", fontWeight = FontWeight.SemiBold)
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (showSpeed) Text("12.8 KB/s", style = MaterialTheme.typography.labelSmall)
                    if (showWifi) Text("⌁", style = MaterialTheme.typography.titleMedium)
                    if (showMobile) Text("▮▮▮", style = MaterialTheme.typography.labelMedium)
                    if (showBattery) Text("86% ▰", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Text("这些图标和数据是静态模拟，不读取手机状态，也不修改 Android 系统状态栏。", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
    }
    SkinFrame(title = "预览项目") {
        SkinSwitchRow("时间", checked = showClock, onCheckedChange = { showClock = it })
        SkinSwitchRow("网络速度", checked = showSpeed, onCheckedChange = { showSpeed = it })
        SkinSwitchRow("Wi-Fi 图标", checked = showWifi, onCheckedChange = { showWifi = it })
        SkinSwitchRow("移动信号", checked = showMobile, onCheckedChange = { showMobile = it })
        SkinSwitchRow("电池图标", checked = showBattery, onCheckedChange = { showBattery = it })
    }
}

@Composable
private fun SettingsPage(
    darkPreview: Boolean,
    onDarkPreviewChange: (Boolean) -> Unit,
    dynamicPreview: Boolean,
    onDynamicPreviewChange: (Boolean) -> Unit
) {
    var selectedMode by remember { mutableStateOf("系统") }
    SkinFrame(title = "主题模式") {
        SkinChoiceChips(listOf("浅色", "深色", "系统"), selectedMode, {
            selectedMode = it
            onDarkPreviewChange(it == "深色")
        }, Modifier.fillMaxWidth())
        SkinPreferenceRow("深色主题预览", "仅改变 Showcase 的主题", trailing = {
            Switch(checked = darkPreview, onCheckedChange = onDarkPreviewChange)
        })
        SkinPreferenceRow("系统动态配色", "Android 12+；使用设备壁纸色板", trailing = {
            Switch(checked = dynamicPreview, onCheckedChange = onDynamicPreviewChange)
        })
    }
    SkinFrame(title = "显示信息") {
        SkinPreferenceRow("AlohaSkin", "独立视觉主题与组件库", trailing = { Text("Demo", color = MaterialTheme.colorScheme.onSurfaceVariant) })
        SkinPreferenceRow("当前范围", "主题、组件和状态栏静态预览")
        SkinPreferenceRow("视觉校准", "原版尺寸/颜色/字体对照仍在进行")
    }
}
