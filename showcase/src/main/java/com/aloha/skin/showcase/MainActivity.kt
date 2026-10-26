package com.aloha.skin.showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateMapOf
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
import com.aloha.skin.theme.SkinThemeMode
import com.aloha.skin.theme.SkinPaletteStyle

private enum class MainPage(val title: String, val icon: String) {
    HOME("主页", "⌂"),
    FEATURES("功能", "◈"),
    STATUS_BAR("状态栏", "▮"),
    SETTINGS("设置", "⚙")
}

private val settingGroups = listOf(
    "DESIGN" to listOf("主题方案", "主题模式", "动态配色", "强调色", "配色过渡"),
    "NAVIGATION_BAR" to listOf("导航栏样式", "浮动导航", "透明表面", "选中状态", "图标与标签"),
    "MODE" to listOf("浅色模式", "深色模式", "跟随系统", "对比度"),
    "LAUNCH" to listOf("启动页样式", "启动图标", "启动动画"),
    "ASSIST" to listOf("触感反馈", "动效偏好", "界面反馈"),
    "SHAPE" to listOf("整体圆角", "卡片形状", "按钮形状", "描边样式"),
    "COMPONENTS" to listOf("按钮", "开关", "滑块", "选择标签", "对话框", "底部弹层"),
    "SHADOWS" to listOf("阴影层级", "浮层高度", "表面层次")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var themeMode by remember { mutableStateOf(SkinThemeMode.SYSTEM) }
            var dynamicPreview by remember { mutableStateOf(false) }
            var selectedSeedIndex by remember { mutableIntStateOf(0) }
            var paletteStyleIndex by remember { mutableIntStateOf(0) }
            var useSystemContrast by remember { mutableStateOf(true) }
            var contrastPreview by remember { mutableFloatStateOf(0f) }
            var selectedPage by remember { mutableStateOf(MainPage.HOME) }
            val themeSeeds = listOf(
                Color(0xFF6750A4),
                Color(0xFF28677B),
                Color(0xFF9A405B),
                Color(0xFF426B45)
            )
            val paletteStyles = listOf(
                SkinPaletteStyle.TONAL_SPOT,
                SkinPaletteStyle.EXPRESSIVE,
                SkinPaletteStyle.VIBRANT,
                SkinPaletteStyle.MONOCHROME
            )
            AlohaSkinTheme(
                themeMode = themeMode,
                dynamicColor = dynamicPreview,
                seedColor = themeSeeds[selectedSeedIndex],
                paletteStyle = paletteStyles[paletteStyleIndex],
                contrastLevel = if (useSystemContrast) null else contrastPreview.toDouble()
            ) {
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
                        Modifier.fillMaxSize().padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        TotalTitleBar()
                        Column(
                            Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState())
                                .padding(horizontal = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            PageOptionFrame(selectedPage) { selectedPage = it }
                            when (selectedPage) {
                                MainPage.HOME -> HomePage()
                                MainPage.FEATURES -> FeaturesPage()
                                MainPage.STATUS_BAR -> StatusBarPage()
                                MainPage.SETTINGS -> SettingsPage(
                                    themeMode = themeMode,
                                    onThemeModeChange = { themeMode = it },
                                    dynamicPreview = dynamicPreview,
                                    onDynamicPreviewChange = { dynamicPreview = it },
                                    selectedSeedIndex = selectedSeedIndex,
                                    onSeedChange = { selectedSeedIndex = it },
                                    paletteStyleIndex = paletteStyleIndex,
                                    onPaletteStyleChange = { paletteStyleIndex = it },
                                    useSystemContrast = useSystemContrast,
                                    onUseSystemContrastChange = { useSystemContrast = it },
                                    contrastPreview = contrastPreview,
                                    onContrastChange = { contrastPreview = it }
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TotalTitleBar() {
    Column(
        Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 14.dp, bottom = 2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text("AlohaSkin", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.SemiBold)
        Text(
            "Voyage-inspired visual theme & components",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun PageOptionFrame(page: MainPage, onPageSelected: (MainPage) -> Unit) {
    SkinFrame(title = "页面") {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MainPage.values().forEach { item ->
                val selected = item == page
                Surface(
                    modifier = Modifier.weight(1f).clip(RoundedCornerShape(14.dp))
                        .clickable { onPageSelected(item) },
                    shape = RoundedCornerShape(14.dp),
                    color = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Column(
                        Modifier.padding(vertical = 10.dp, horizontal = 2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(item.icon, color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(
                            item.title,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomePage() {
    // Intentionally sparse: the global title, page-option frame and bottom navigation remain visible.
    Spacer(Modifier.height(4.dp))
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
        SkinFrame(title = "浮动导航表面") { Text("主导航栏固定在屏幕底部；此处为组件示例。") }
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
        Surface(shape = MaterialTheme.shapes.medium, color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.fillMaxWidth()) {
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
        Text("图标和数据是静态模拟，不读取手机状态，也不修改系统状态栏。", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
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
    themeMode: SkinThemeMode,
    onThemeModeChange: (SkinThemeMode) -> Unit,
    dynamicPreview: Boolean,
    onDynamicPreviewChange: (Boolean) -> Unit,
    selectedSeedIndex: Int,
    onSeedChange: (Int) -> Unit,
    paletteStyleIndex: Int,
    onPaletteStyleChange: (Int) -> Unit,
    useSystemContrast: Boolean,
    onUseSystemContrastChange: (Boolean) -> Unit,
    contrastPreview: Float,
    onContrastChange: (Float) -> Unit
) {
    var selectedSetting by remember { mutableStateOf<String?>(null) }
    var selectedTab by remember { mutableIntStateOf(0) }
    val expandedGroups = remember { mutableStateMapOf<String, Boolean>() }
    val values = remember { mutableStateMapOf<String, String>() }
    val switches = remember { mutableStateMapOf<String, Boolean>() }
    val seedNames = listOf("Violet", "Ocean", "Rose", "Forest")
    val styleNames = listOf("Tonal Spot", "Expressive", "Vibrant", "Monochrome")
    val modeName = when (themeMode) {
        SkinThemeMode.SYSTEM -> "系统"
        SkinThemeMode.LIGHT -> "浅色"
        SkinThemeMode.DARK -> "深色"
    }

    if (selectedSetting != null) {
        SettingDetailPage(
            title = selectedSetting!!,
            selectedTab = selectedTab,
            onTabChange = { selectedTab = it },
            onBack = { selectedSetting = null },
            values = values,
            switches = switches
        )
        return
    }

    SkinFrame(title = "主题外观") {
        Text("主题模式", style = MaterialTheme.typography.titleSmall)
        SkinChoiceChips(listOf("系统", "浅色", "深色"), modeName, {
            onThemeModeChange(
                when (it) {
                    "浅色" -> SkinThemeMode.LIGHT
                    "深色" -> SkinThemeMode.DARK
                    else -> SkinThemeMode.SYSTEM
                }
            )
        }, Modifier.fillMaxWidth())
        SkinPreferenceRow("系统动态配色", "Android 12+ 系统调色板；开启后优先于自定义种子色", trailing = {
            Switch(checked = dynamicPreview, onCheckedChange = onDynamicPreviewChange)
        })
        Text("自定义种子色", style = MaterialTheme.typography.titleSmall)
        SkinChoiceChips(seedNames, seedNames[selectedSeedIndex], { chosen ->
            onSeedChange(seedNames.indexOf(chosen).coerceAtLeast(0))
        }, Modifier.fillMaxWidth())
        Text("调色板风格", style = MaterialTheme.typography.titleSmall)
        SkinChoiceChips(styleNames, styleNames[paletteStyleIndex], { chosen ->
            onPaletteStyleChange(styleNames.indexOf(chosen).coerceAtLeast(0))
        }, Modifier.fillMaxWidth())
        SkinPreferenceRow("跟随系统对比度", "Android 14+ 可读取系统对比度；其他版本回退到标准对比度", trailing = {
            Switch(checked = useSystemContrast, onCheckedChange = onUseSystemContrastChange)
        })
        if (!useSystemContrast) {
            SkinSliderRow("对比度", contrastPreview, onContrastChange, -1f..1f)
        }
        Text(
            "种子色与调色板仅控制视觉配色；当前选项保存在 Showcase 内存中。",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodySmall
        )
    }
    Text("主题设置", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
    settingGroups.forEach { (group, options) ->
        SkinAccordion(
            title = group,
            expanded = expandedGroups[group] ?: false,
            onExpandedChange = { expandedGroups[group] = it }
        ) {
            options.forEach { option ->
                SkinPreferenceRow(
                    title = option,
                    subtitle = "外观选项 · 仅本地展示状态",
                    trailing = { Text("›", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurfaceVariant) },
                    onClick = { selectedSetting = option; selectedTab = 0 }
                )
            }
        }
    }
    SkinFrame(title = "其他设置样例") {
        SkinPreferenceRow("主题详情", "调色板、组件状态和详细选项", trailing = { Text("›") }, onClick = { selectedSetting = "主题详情" })
        SkinPreferenceRow("关于 AlohaSkin", "独立主题与组件 Showcase", trailing = { Text("›") }, onClick = { selectedSetting = "关于 AlohaSkin" })
    }
}

@Composable
private fun SettingDetailPage(
    title: String,
    selectedTab: Int,
    onTabChange: (Int) -> Unit,
    onBack: () -> Unit,
    values: MutableMap<String, String>,
    switches: MutableMap<String, Boolean>
) {
    var expandedLevel by remember(title) { mutableIntStateOf(1) }
    SkinFrame(title = "设置详情") {
        SkinPreferenceRow("返回设置列表", title, trailing = { Text("‹", style = MaterialTheme.typography.titleLarge) }, onClick = onBack)
    }
    SkinFrame(title = title) {
        SkinTabs(listOf("选项", "样式", "详情"), selectedTab, onTabChange)
        Spacer(Modifier.height(12.dp))
        when (selectedTab) {
            0 -> {
                SkinPreferenceRow("一级选项", "基础设置项", trailing = { Text(values["$title:一级"] ?: "默认") }, onClick = { values["$title:一级"] = if (values["$title:一级"] == "已选择") "默认" else "已选择" })
                SkinAccordion("二级选项", expanded = expandedLevel >= 2, onExpandedChange = { expandedLevel = if (it) 2 else 1 }) {
                    SkinPreferenceRow("二级 · 样式选择", "可切换的本地选项", trailing = { Text(values["$title:二级"] ?: "跟随主题") }, onClick = { values["$title:二级"] = if (values["$title:二级"] == "自定义") "跟随主题" else "自定义" })
                    SkinAccordion("三级选项", expanded = expandedLevel >= 3, onExpandedChange = { expandedLevel = if (it) 3 else 2 }) {
                        SkinPreferenceRow("三级 · 细节参数", "更深层的视觉选项", trailing = { Text(values["$title:三级"] ?: "默认") }, onClick = { values["$title:三级"] = if (values["$title:三级"] == "精细") "默认" else "精细" })
                        SkinAccordion("更多详情", expanded = expandedLevel >= 4, onExpandedChange = { expandedLevel = if (it) 4 else 3 }) {
                            SkinSwitchRow("高级视觉选项", "仅改变此页面的示例状态", switches["$title:高级"] ?: false, { switches["$title:高级"] = it })
                            SkinSliderRow("细节强度", (values["$title:强度"]?.toFloatOrNull() ?: 50f), { values["$title:强度"] = it.toInt().toString() }, 0f..100f)
                        }
                    }
                }
            }
            1 -> {
                SkinChoiceChips(listOf("默认", "柔和", "强调"), values["$title:样式"] ?: "默认", { values["$title:样式"] = it }, Modifier.fillMaxWidth())
                SkinFrame(title = "组件状态预览") {
                    SkinButtonPair("选中状态", "未选中状态", {}, {}, enabled = true)
                    SkinSwitchRow("示例开关", checked = switches["$title:样式开关"] ?: true, onCheckedChange = { switches["$title:样式开关"] = it })
                }
            }
            else -> {
                SkinPreferenceRow("当前项目", title)
                SkinPreferenceRow("应用范围", "所有页面均使用共享主题色、形状和文字层级")
                SkinPreferenceRow("数据范围", "本地演示状态；离开页面不承诺持久保存")
                SkinFrame(title = "色彩与表面") {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.outline).forEach {
                            Box(Modifier.weight(1f).height(34.dp).clip(MaterialTheme.shapes.small).background(it))
                        }
                    }
                }
            }
        }
    }
}
