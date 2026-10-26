# AlohaSkin 维护总记录

> 本文件是 AlohaSkin 项目的唯一持续维护总记录，存放于仓库根目录。**每次继续维护都必须先完整阅读本文件，并在本次工作结束前向文件末尾追加新的带时间记录**；不得覆盖或删除既有历史记录。若维护工作尚未完成，应明确记录未完成项和下一步，不得把阶段性实现写成最终完成。

## 项目目标与固定规则

- 仓库：`https://github.com/iskyxpcity/AlohaSkin`
- 当前工作分支：`feature/voyage-visual-system-extraction-20261026`
- 目标：从 Voyage 的已解包 APK、反编译代码和资源中，提取**视觉风格、主题系统和可复用 UI 组件**，形成独立的 AlohaSkin 主题/组件库。
- 不再将 Voyage 设置功能移植到 Aloha OneUI；本项目与 Aloha OneUI 的功能代码分开维护。
- 只做视觉层：颜色/主题令牌、字体层级、间距、形状、圆角、描边、阴影、表面与框体、组件布局、视觉状态、纯视觉动效规格。
- 必须剥离 Voyage 的业务功能：不移植其设置持久化、业务导航、服务、权限、数据模型、系统副作用或应用工作流。展示页只使用本地演示状态。
- 不可仅凭相似外观声称 1:1。所有标记为完成的组件都必须有参考依据、浅色/深色状态截图或可核验视觉记录，以及尺寸/颜色/间距/形状等核对结果。
- 反编译结果是视觉研究证据，不代表原始源码的授权。只在许可和来源明确允许时复用原资产/代码；否则应独立实现视觉系统，并记录依赖与许可。
- 每次续作：先读本文件；继续真实代码/验证工作；完成后在本文件末尾追加带时间、提交 SHA、构建状态、已完成/未完成内容和下一步的记录；再把本文件提交到仓库根目录。不得静默改写旧记录。

## Voyage 参考证据

- 原始 APK SHA-256：`b74d1f49c152e1fdd1af8fd1e36fdedebe283f3c1e740066f281857c0682c0b1`
- 已检查的反编译类/资源包括 `ThemeSettingsRoute`、`ThemeSchemeEditorRoute`、`vfw0`、`eja0`、`dgw0`、`zcj0`、`k9t0`，以及资源中的主题和字符串定义。
- 已观察到的 Voyage 主题设置页面八个可展开分组顺序：`DESIGN`、`NAVIGATION_BAR`、`MODE`、`LAUNCH`、`ASSIST`、`SHAPE`、`COMPONENTS`、`SHADOWS`。这些仅作为组件外观/布局的研究样本，不代表 AlohaSkin 要实现相应功能。
- 初始组件清单与提取步骤见 `docs/COMPONENT_INVENTORY.md`、`docs/EXTRACTION_PLAN.md`。

## 维护历史

### K.001 — 2026-10-26：建立独立项目与初始组件库

**工作范围调整**
- 按用户最新要求，停止把 Voyage 的设置页面和功能移植到 Aloha OneUI。
- 改为在独立仓库 AlohaSkin 提取 Voyage 的主题风格、组件、容器/框体和视觉状态，并剥离其业务功能。

**仓库与分支**
- 从 `main` 创建工作分支：`feature/voyage-visual-system-extraction-20261026`。
- 本次维护代码分支 HEAD（截至本记录生成时）：`539baecbe7842d774b6cb7cf11876be69db5732b`。
- 最近已知提交：
  - `4f3618d1c80661a54ccc81efe3561277489a768c` — 建立项目说明、提取计划和组件清单。
  - 后续提交建立 Android Compose library 与 showcase、补充基础主题/组件，并修复 Java/Kotlin JVM target 对齐；最新提交为 `539baecbe7842d774b6cb7cf11876be69db5732b`（`fix: align Java and Kotlin JVM targets`）。

**已创建的项目文件**
- `README.md`：定义视觉提取边界、目标和状态。
- `docs/EXTRACTION_PLAN.md`：提取阶段、证据来源和验收标准。
- `docs/COMPONENT_INVENTORY.md`：组件家族及状态清单。
- `docs/IMPLEMENTATION_STATUS.md`：初步实现与待办状态。
- `settings.gradle.kts`、根 `build.gradle.kts`、`gradle.properties`：Android/Compose 项目配置。
- `aloha-skin/`：独立 Android library module。
- `showcase/`：独立的组件展示 Demo。
- `.github/workflows/build.yml`：构建 library 和 showcase、上传调试 APK 的 CI 工作流。

**目前已写入代码的初步组件**
- Light/Dark 语义色彩方案。
- 圆角描边框体 `SkinFrame`。
- 可展开分组 `SkinAccordion`，带尺寸过渡与箭头旋转。
- 设置/偏好行 `SkinPreferenceRow`。
- 开关行 `SkinSwitchRow`。
- 选中/未选中按钮 `SkinActionButton`。
- 选择 chips `SkinChoiceChips`。
- 滑块行 `SkinSliderRow`。
- 展示页面中的控件只操作本地演示状态，不连接 Voyage 的功能或系统行为。

**准确性声明**
- 当前配色、圆角、组件布局是初始实现，不是已验证的 Voyage 像素级复刻。
- 尚未完成原版尺寸/颜色/字体/阴影/动效测量，也尚未完成组件截图逐项对照。
- 当前展示页面只覆盖少量样本组件；完整组件库尚未完成。
- 不得将目前状态对外描述为“已完成一比一提取”。

**构建状态**
- GitHub Actions 工作流：`AlohaSkin CI`。
- 本次观察到的运行：Run ID `40313767028`，对应提交 `539baecbe7842d774b6cb7cf11876be69db5732b`，观察时状态为 `in_progress`，结论尚未产生。
- 工作流运行链接：`https://github.com/iskyxpcity/AlohaSkin/actions/runs/40313767028`。
- 在取得明确的完成状态和构建产物前，不得声称构建成功或提供最终 APK 交付。

**下一步**
1. 检查当前 CI 最终结果；如失败，读取完整日志并修复后重跑。
2. 阅读 Voyage 资源中与主题颜色、字体、shape、尺寸、outline、elevation、组件状态有关的证据，记录可核验的参数和来源。
3. 补充颜色/排版/形状/间距/阴影/动效 token，逐项替换尚未校准的临时值。
4. 实现并展示 tabs、对话框、bottom sheets、palette/theme cards、完整按钮/开关/滑块状态等尚缺组件。
5. 建立浅色与深色参考截图对照；每个组件记录偏差和验收状态。
6. 完成 API/使用说明、许可审查、自动化测试和可复现构建。
7. 下次续作前先完整阅读本记录，并在末尾追加 K.002，不覆盖 K.001。

### K.002 — 2026-10-26：扩展可复用组件与视觉证据台账

**本次执行**
- 续作前已完整读取当前维护总记录，按规则在既有历史末尾追加本节。
- 在 `aloha-skin` 中新增 `SkinAdvancedComponents.kt`，补入通用 Surface 容器、Tabs、主题调色板预览卡、主/描边按钮组合、通用确认对话框外壳。
- 更新 Showcase，增加 Tabs、主题预览卡、按钮状态样例；所有控件仅操作 Demo 本地状态，不连接 Voyage 业务逻辑。
- 新增 `docs/VISUAL_EVIDENCE.md`，将已观察事实与待测参数分开登记，明确不得猜测原版尺寸/颜色/动效值。
- 更新组件清单和实现状态文档，注明新增组件仍未通过像素级对照，不宣称 1:1 完成。

**代码提交**
- 本次组件与文档提交：`cb7da3b3ec9cf48e71159221cf27e794eb65ed6a`（`feat: expand reusable skin components and showcase`）。
- 本维护记录提交基于该提交追加；工作分支仍为 `feature/voyage-visual-system-extraction-20261026`。
- `main` 未修改、未合并。

**CI 状态**
- 本次代码提交对应 Actions Run ID `40314648720`：`https://github.com/iskyxpcity/AlohaSkin/actions/runs/40314648720`。
- 写入本条记录时，构建步骤仍为 `in_progress`，尚无最终结论；因此本条不宣称本轮代码已构建成功。
- 早期提交曾因 Java/Kotlin JVM target 不一致失败；修正提交 `539baecbe7842d774b6cb7cf11876be69db5732b` 的后续构建 Run ID `40313767028` 已成功。当前新增组件仍需以本轮 CI 最终结果为准。

**未完成事项**
1. 获取当前 Run 的最终状态；失败时读取日志并修复。
2. 从 Voyage 资源中继续核对准确的色彩、文字样式、边距、组件高度、圆角、描边、阴影和动效参数。
3. 将设置行补齐 leading slot，补充 bottom sheet、更多控件状态与可访问性语义。
4. 在浅色/深色主题中建立逐组件截图和偏差表。
5. 审查依赖和可复用资产许可，完善公开 API 示例与测试。

**下次续作要求**
- 先完整阅读本文件；接着处理当前 CI 结果和原版视觉证据核对；结束时继续追加 K.003，不改写 K.001/K.002。

### K.003 — 2026-10-26：补齐组件外壳并审计资源证据

**本次执行**
- 在 `SkinPreferenceRow` 中加入可选 leading slot，使图标/前导内容可由调用方提供；不绑定具体业务。
- 新增 `SkinBottomSheet` 通用 ModalBottomSheet 外壳，内容和显隐状态由调用方传入。
- Showcase 增加 Dialog 与 Bottom Sheet 交互样例；只控制本地展示状态。
- 对反编译资源包的 `colors.xml`、`dimens.xml` 做初步分类，新增资源证据审计：明确 `glance_color*` 属于 Glance/widget 资源，第三方库的通用 dimens 不能直接视为 Voyage 主 Compose 页面参数。没有把不相关资源硬套进主题令牌。

**代码提交**
- 组件提交：`8cf5490503c2a3bf50ed6f49bfa683a4a6f8e675`（leading slot + bottom sheet）。
- Showcase 提交：`001b344e19acac783e87a86fcd73fb111d1c514f`（Dialog/Sheet 展示）。
- 本条维护记录和资源审计随本次提交追加；仍在测试分支，`main` 未修改/合并。

**构建状态**
- 组件扩展提交 `cb7da3b3ec9cf48e71159221cf27e794eb65ed6a` 的 CI Run `40314648720` 已成功。
- 后续加入 Bottom Sheet 和 Showcase 的 Runs `40315029975`、`40315169689` 在写入本条时仍处于 `in_progress`，没有最终结论。不能把早期成功构建等同于最新 HEAD 构建通过。
- Run `40315169689`：`https://github.com/iskyxpcity/AlohaSkin/actions/runs/40315169689`。

**尚未完成**
1. 继续取得最新 CI 结果；失败则读日志、修复并重新构建。
2. 从真正的 Compose 主题调用点和组件实现中继续提取可验证参数；不把 Glance/第三方库资源当作主题证据。
3. 对每个组件补齐浅色/深色截图、选中/未选中、启用/禁用、按压/焦点状态和尺寸偏差。
4. 完善底部导航/玻璃表面等组件（只在源码证据足够时实现），补充测试与 API 示例。
5. 完成许可/依赖审查和独立库交付验证。

**下次续作**
- 先完整阅读本记录，先处理上述 CI Runs 的最终结果；随后继续源证据核验与组件视觉校准，并追加 K.004。

### K.004 — 2026-10-26：验证展示构建并记录可核验的展开分组证据

**构建与产物验证**
- 组件 + Dialog/Bottom Sheet 展示代码提交 `001b344e19acac783e87a86fcd73fb111d1c514f` 的 Actions Run `40315169689` 已完成且成功：`https://github.com/iskyxpcity/AlohaSkin/actions/runs/40315169689`。
- 该运行产物 Artifact ID `12645784497`，名称 `AlohaSkin-Showcase-Debug-APK`，ZIP 大小 `8,352,265 bytes`，Artifact 声明 SHA-256：`5eb7b38019b575c2698c200fe5cd208426b52f3b8ec0b9af99c05805774dca9a`。
- 已下载到本地并验证 ZIP 完整性；其中 `showcase-debug.apk` 大小 `8,756,175 bytes`，SHA-256 `ff80cd6387876fb4c2a6dac7ba2602e8ec905c1202ae2f8cf1dee3b29789482e`。这是测试展示 APK，不是最终发布版。
- 加入 bottom sheet 的代码提交 `8cf5490503c2a3bf50ed6f49bfa683a4a6f8e675` 对应 Run `40315029975` 也已成功。
- 维护记录/资源审计提交 `88354402931932914ce7f2019e03c156fb21f25e` 的文档变更触发 Run `40315416196`；写入本条时仍在运行，代码本身的最近一次成功构建证据仍为 `40315169689`。

**源码证据增量**
- 继续检查反编译 `p000/vfw0.java` 的 `m137940Oo`：确认展开指示符旋转目标为收起 0°、展开 180°；内部存在 8.0f 的布局 helper 调用；颜色通过当前主题色系进行 0.5f 混合。
- 已将这些发现追加到 `docs/VISUAL_EVIDENCE.md`，并明确动画时长/曲线、helper 的精确布局语义仍待确认，不把它们当作已测定的最终参数。

**本次提交**
- 当前提交将资源证据台账与本维护记录继续追加，保留 K.001–K.003 历史；`main` 未修改、未合并。

**未完成与下一步**
1. 等待 Run `40315416196` 结束；若失败，检查日志并修复。
2. 进一步检查 `vfw0` 的布局 helper、颜色混合路径和主题调用点，确认哪些数值真正属于 Voyage 主 Compose UI。
3. 补充真实的浅/深色截图与组件状态截图；当前 APK 只证明构建成功，不证明视觉一致。
4. 继续实现并校准主题令牌、组件状态、表面/框体、导航表面；任何未测量的值保持标记为 provisional。
5. 续作时先读完整记录，再追加 K.005。



### K.005 — 2026-10-26：新增浮动导航与半透明表面组件

**本次执行**
- 续作前已读取当前维护总记录及组件清单、实现状态、视觉证据台账。
- 新增 `SkinFloatingNavigationBar` 与 `SkinNavigationItem`，支持浮动圆角容器、可选半透明/玻璃感表面、选中/未选中状态和由宿主提供的图标/点击回调。
- Showcase 新增导航栏展示，切换项仅修改 Demo 的本地选择索引，不执行实际导航。
- 更新组件清单、实现状态与视觉证据台账。透明度、圆角、描边、阴影和项目尺寸均明确标为临时实现，尚未证明与 Voyage 1:1。

**代码与分支**
- 代码基线：`f3daf69315619a576d06202193b00b5a41297f0f`。
- 本次提交完成后填写实际提交 SHA；仍在 `feature/voyage-visual-system-extraction-20261026`，不得修改或合并 `main`。

**构建基线**
- 最近已知成功运行：Actions Run `40315759141`，提交 `f3daf69315619a576d06202193b00b5a41297f0f`，结论 `success`。
- 对应 Showcase Artifact ID `12646762259`，大小 `8,352,264 bytes`，ZIP digest `sha256:78e5c9bdbbe24894d23b9d9696eebe86bd657adac80310478f254032aba52d45`。该产物仅是之前版本的测试 APK；本次新增导航组件必须等待新的 CI 构建通过后才算构建验证。

**尚未完成**
1. 等待本次提交的 CI 结果；若失败，读取日志并修复后重跑。
2. 从 Voyage 实际 Compose 主题调用点与截图提取可验证的颜色、字体、尺寸、阴影、形状和动效值；目前令牌仍属 provisional。
3. 完善无障碍语义、按压/禁用/焦点状态，以及更多框体和导航变体。
4. 建立浅色/深色参考图与实现截图的对照表，记录偏差，不得猜测原版值。
5. 完成独立库 API、依赖/许可审查、测试和可复现构建。

**下一步**
- CI 完成后先验证新 APK 构建；然后继续主题令牌与状态组件的源证据提取，结束时追加 K.006。


### K.006 — 2026-10-26：补充系统动态配色入口与禁用控件状态

**本次执行**
- 在 `AlohaSkinTheme` 增加可选 `dynamicColor` 参数；Android 12/API 31 及以上可使用系统 Material 3 动态浅色/深色配色，关闭或旧系统回退到临时语义色板。
- `SkinActionButton` 与 `SkinChoiceChips` 增加显式禁用参数和基础禁用视觉表现；默认参数保持现有调用兼容。
- 更新 README、实现状态和视觉证据台账，说明 Voyage 的 MaterialKolor 自定义 seed/palette、系统对比度适配和配色动画尚未移植；不将当前临时色值宣称为原版准确色值。
- 该修改仍只属于主题/组件表现层，不包含设置保存或 Voyage 功能。

**代码与构建**
- 本条基于提交 `bcc41fe45e16e6b91a67f0f336308c008b812365` 继续。
- 上一提交 Run `40322008475` 在本记录追加前仍处于运行状态；本次新代码也需以对应最新 CI 的最终结论为准。
- 提交 SHA 在创建提交后可通过本条下一次维护记录补充，避免写入未确定的哈希。

**未完成与下一步**
1. 检查导航栏提交 Run `40322008475` 的最终构建状态；如失败，读取日志并修复。
2. 验证动态配色提交的构建；补充测试覆盖 Android 版本分支。
3. 继续核对 Voyage 真实主题调用点，评估 MaterialKolor 依赖、系统对比度与色彩过渡的纯视觉实现。
4. 建立浅/深色、选中/未选中、禁用状态截图对照；当前尚无像素级 1:1 认证。
5. 后续继续追加 K.007，不覆盖此前历史。


### K.007 — 2026-10-26：集中视觉令牌并接入动态配色预览

**本次执行**
- 继续前已完整读取本维护总记录，并核验当前分支和上一轮 CI。
- 新增 `aloha-skin/src/main/java/com/aloha/skin/theme/SkinTokens.kt`，集中定义 provisional spacing、radii、stroke、elevation 与 Material 3 `Shapes`；`AlohaSkinTheme` 现已统一应用 `SkinShapes`。
- Showcase 增加系统动态配色开关，直接验证 `AlohaSkinTheme(dynamicColor = ...)` 路径；同时把原来误命名为动态配色样例、实际只控制 Demo 控件启用状态的开关改成“Sample controls enabled”。
- 更新 `docs/IMPLEMENTATION_STATUS.md` 与 `docs/VISUAL_EVIDENCE.md`，明确令牌是集中管理，不代表已完成 Voyage 测量；组件中仍有未统一的字面尺寸。
- 所有改动继续只涉及主题视觉与 Showcase 本地状态，不导入 Voyage 的业务功能、持久化、导航或系统副作用。

**本轮提交**
- `39e2a1c41254f896436d0c4bb1f5b41c63255529` — `feat: centralize provisional visual design tokens`
- `b4bbdf4a88bb4674d272a6b6c183a60259a00d7c` — `refactor: apply shared skin shape tokens`
- `08bdc92d9f4f3a87709fb384686eecb129cd1353` — `feat: expose dynamic palette in showcase`
- `7185e01b269f98ce2e6f16cab3fb3534754728a0` — `docs: track shared tokens and dynamic palette preview`
- `eff47987503ae715f1f82c10dc61f2f17042e0d6` — `docs: record shared tokens and dynamic preview evidence`
- 分支仍为 `feature/voyage-visual-system-extraction-20261026`；`main` 未修改、未合并。
- 历史 K.005 曾留下“提交 SHA 后续填写”的占位文字；此处保留历史原文，并通过本条补记：K.005 导航代码提交实际为 `bcc41fe45e16e6b91a67f0f336308c008b812365`，其后动态配色/禁用态提交为 `650f8c8022a88efe1b029121e54a4ba6529597f5`。

**CI 与测试 APK**
- Run #11 `40322277172` 对应提交 `650f8c8022a88efe1b029121e54a4ba6529597f5` 已完成且成功；Build library and showcase、Upload showcase APK 两步均成功。
- Run #11 Artifact ID `12649627278`，名称 `AlohaSkin-Showcase-Debug-APK`，大小 `8,361,971 bytes`，ZIP digest `sha256:2b4925624e3d08e09055f3bd490f643a087eb0632f171e2945d152ec93a780cc`；链接：`https://github.com/iskyxpcity/AlohaSkin/actions/runs/40322277172`。
- Run #11 APK 是提交 `650f8c8` 的调试展示版，不包含 K.007 的新令牌/展示修改，不能作为当前 HEAD 的最终交付。
- 当前新代码触发的 Run #14 `40323068240`（提交 `08bdc92d9f4f3a87709fb384686eecb129cd1353`）与 Run #15 `40323108507`（提交 `7185e01b269f98ce2e6f16cab3fb3534754728a0`）在追加本条前观察时仍为 `in_progress`；须继续核对，不得推断为成功。
- 本维护记录提交会触发新的 CI；最终状态和最新 artifact 由后续维护记录补记。

**未完成事项**
1. 确认 Run #14/#15 和本条记录提交所触发 CI 的最终结果；失败时读取日志、修复并重跑。
2. 取得当前 HEAD 对应的 APK artifact，并确认上传步骤成功；之前的 artifact 不可替代当前构建。
3. 继续审计 Voyage 主题调用点、MaterialKolor 自定义 palette/seed、Android 14+ 对比度适配和配色动画。
4. 建立真实浅/深色参考截图与 Showcase 截图的测量/偏差表；未测值继续标记 provisional，不能宣称 1:1。
5. 完善组件状态、无障碍语义、测试、API 使用说明和依赖/许可审查；最后再做完整交付验收。

**下次续作**
- 先完整读取本记录，优先处理所有最新 CI 结果并拿到与当前 HEAD 对应的 APK，然后继续实现与验证；追加 K.008，不覆盖历史。


### K.008 — 2026-10-26：完成共享令牌接入并验证可交付 Showcase APK

**本次执行**
- 续作前完整读取维护总记录；在 K.007 之后继续将共享令牌应用到核心组件与高级组件。
- `SkinAdvancedComponents.kt` 中的 surface、palette card、button pair 和 bottom sheet 开始消费共享的圆角、间距、描边和 elevation token。
- `SkinComponents.kt` 中的 frame、accordion、preference row、choice chips、slider row 使用共享 token 作为默认尺寸来源。仍有个别组件值和文字样式未统一，且全部 token 都仍是 provisional，不能据此宣称已按 Voyage 源码测量。
- 更新 `docs/IMPLEMENTATION_STATUS.md`，登记最新代码构建、产物 ID、ZIP 与 APK 的 SHA-256，并把“等待最新构建/归档”改为已验证的调试 Showcase 产物。

**代码提交**
- `f4366a1d179f820f9b6da37c50c94781debd25ab` — `refactor: consume shared tokens in advanced components`。
- `ca7fc5e73b010649e809eef81955a86365d2c908` — `refactor: normalize core components to shared tokens`。
- `57ea0d224987e7b186ca470e6c76a35c0e4d8ab5` — `docs: record verified showcase debug APK`。
- 本条维护记录追加后会产生新的记录提交；本条中记录的构建源代码 SHA 以 `ca7fc5e` 为准。
- 工作分支仍为 `feature/voyage-visual-system-extraction-20261026`；`main` 未修改、未合并。

**构建与 APK 验证**
- AlohaSkin CI Run #19 对代码提交 `ca7fc5e73b010649e809eef81955a86365d2c908` 已完成且成功：`https://github.com/iskyxpcity/AlohaSkin/actions/runs/40323387206`。
- Library 与 Showcase 两个 assembleDebug 构建步骤成功，`Upload showcase APK` 成功。
- Artifact 名称：`AlohaSkin-Showcase-Debug-APK`；Artifact ID：`12650342383`；ZIP 大小：`8,366,076 bytes`；ZIP SHA-256：`7af01710431e8e1caa097611131133cd7397c09f30f772594d182a670807bc9f`。
- 从 ZIP 解出的 `showcase-debug.apk` 大小：`8,772,559 bytes`；APK SHA-256：`26a4c2b1f11ddb0f08d4394a1ab8c5a656684d6aff9f547fd26a81f37796ff03`。已通过 ZIP 完整性检查，APK 文件结构检查未发现压缩包错误。
- 该文件是可安装的 **Debug Showcase APK**，可用于查看当前组件展示与试验主题；它不是签名生产版，也不是 AlohaSkin 视觉系统 1:1 完成证明。
- 本地交付文件名：`AlohaSkin_Showcase_Debug_20261026.apk`。

**仍未完成（不得标记为 1:1 完成）**
1. Voyage 真实 Compose 主题调用点和 MaterialKolor 自定义 seed/palette、Android 14+ contrast adaptation、色彩过渡动画的完整核验/实现。
2. 为 Voyage 原版浅色/深色页面和 Showcase 组件采集可对照截图，测量边距、字体、圆角、描边、颜色、阴影和动效，并记录偏差。
3. 将剩余字面尺寸统一到 tokens；补齐按压/焦点/禁用等状态、无障碍语义与自动化测试。
4. 完成 API 示例、依赖与许可审查；在视觉证据充分且所有验收项通过前，不修改或合并 `main`。
5. 本条维护记录自身提交后的 CI 仍需观察；如果其构建失败，继续读日志、修复和重跑。

**下次续作**
- 先完整读取本文件，检查本条记录提交触发的 CI 最终状态与产物；之后继续处理视觉测量/对照和未完成验收项，追加 K.009，不覆盖旧记录。


### K.009 — 2026-10-26：将 Showcase 改为四个主导航页面

**用户反馈与本轮目标**
- 用户指出先前 APK 只是展示零散组件，要求具备四个主导航页面：主页、功能、状态栏、设置。
- 本轮将展示应用的整体信息架构升级为四个可切换页面，而不是继续把全部组件堆在一个长页面里。

**实现内容**
- 重写 Showcase 主页面，增加四个主导航项目：`主页`、`功能`、`状态栏`、`设置`。
- 底部浮动导航常驻，当前选中项具有独立选中态；各页面内容区域可滚动。
- **主页**：AlohaSkin 项目概览、视觉色板预览、跳转至功能/设置的快捷入口。
- **功能**：将基础控件、表面/容器、配色卡片、对话框和底部弹层按页内 Tabs 分组展示。
- **状态栏**：新增静态状态栏预览，可本地切换时间、网络速度、Wi-Fi、移动信号和电池的显示；这些值均为模拟，不读取设备状态、不修改 Android 实际状态栏。
- **设置**：提供浅色/深色预览和 Android 12+ 系统动态配色开关，以及实现范围说明。
- 更新 `docs/IMPLEMENTATION_STATUS.md`，写明四页范围、导航为本地 Showcase 状态，且当前四页版本尚须通过最新 CI 才算构建验证。
- 所有导航只切换本地页面状态，不加入 Voyage 业务导航、持久化或系统副作用。

**提交**
- 四页导航代码提交：`ac1df402f6ab12bdecf3f0a9075d00444839d10c`（`feat: add four primary showcase navigation pages`）。
- 实现状态文档提交：`c2596644ce0e455eca786922ac242ac0dcfc35b6`（`docs: document four-page showcase navigation`）。
- 工作分支仍为 `feature/voyage-visual-system-extraction-20261026`；`main` 未修改、未合并。
- 本条记录追加会产生新的提交和 CI 运行；最新 CI 状态须继续核验。

**验收状态**
- 代码层面已建立四页信息架构及底部主导航。
- 视觉效果仍属 Showcase 初版，未通过 Voyage 参考截图 1:1 对照；状态栏页面是静态模拟 UI，不是实际状态栏定制功能。
- 在四页导航提交对应 CI 成功、获取与最新源码 SHA 匹配的 APK 之前，不交付旧 APK 冒充新版本。

**未完成与下一步**
1. 检查四页导航提交及后续文档/维护记录提交触发的 CI；若失败，读取日志、修复并重跑。
2. 下载最新成功运行的 Showcase APK，核对 artifact、ZIP 完整性与 APK 文件结构，并记录 SHA-256。
3. 实机检查四页底部导航、页面滚动、主题切换与状态栏模拟布局。
4. 继续补齐视觉参数证据、无障碍语义、组件状态、自动化测试与许可审查；未完成前不得称为 Voyage 1:1。
5. 下一次续作先完整读取本文件，再追加 K.010，不覆盖历史。


### K.010 — 2026-10-26：四页 Showcase 构建成功并交付 APK

**构建验证结果**
- 四页主导航代码提交 `ac1df402f6ab12bdecf3f0a9075d00444839d10c` 对应 Actions Run #22 / ID `40325376216` 已完成且成功：`https://github.com/iskyxpcity/AlohaSkin/actions/runs/40325376216`。
- `Build library and showcase` 和 `Upload showcase APK` 两个步骤均成功。
- Artifact：`AlohaSkin-Showcase-Debug-APK`；Artifact ID `12650308983`；ZIP 大小 `8,385,690 bytes`；ZIP SHA-256 `56b817ebaf797017428f10ee42617b44a38a48397e2e6a4d673aac8d0f0a52e9`。
- 已下载并通过 ZIP 完整性检查（`testzip=None`），内部仅有 `showcase-debug.apk`。
- 解出 APK 大小 `8,788,943 bytes`；APK SHA-256 `51454f71822658e33c3b679eeba9f369cb7814f3d2fe0d4c1e413fd6b0e0b8eb`；APK 文件以 ZIP 头 `PK\x03\x04` 开始，结构检查通过。
- 本地文件名：`AlohaSkin_Showcase_Four_Pages_20261026.apk`。
- 该 APK 是四页导航版本的可安装 Debug Showcase；不是签名生产发布版，也不代表 Voyage 视觉 1:1 验收已完成。

**产物交付内容**
- 主页、功能、状态栏、设置四个主导航页面已在上述构建源代码中实现。
- 底部浮动主导航固定显示，页面切换和控件状态均为本地 Demo 状态。
- 状态栏页只呈现模拟时间、网速、Wi-Fi、移动信号、电池样式，不访问真实设备状态，不改变 Android 系统状态栏。

**当前 HEAD / CI**
- 四页代码提交 `ac1df402f6ab12bdecf3f0a9075d00444839d10c` 已有成功构建和匹配 APK。
- 后续实现状态文档提交 `c2596644ce0e455eca786922ac242ac0dcfc35b6` 对应 Run `40325410587` 已成功。
- 本维护记录提交前最新维护记录提交 `fa1591ecf56e4b4e7a4e7a54871b42c2ac81ff5a` 的 CI Run `40325448863` 在本条写入时仍为 `in_progress`；需要后续检查，但它不改变四页代码提交已有的成功构建证据。
- 工作分支仍为 `feature/voyage-visual-system-extraction-20261026`；`main` 未修改、未合并。

**尚未完成**
1. 实机安装并逐页检查底部导航、系统栏 Insets、页面滚动、动态配色和小屏布局。
2. 继续从 Voyage 参考证据校准颜色、字体、圆角、间距、描边、阴影和动效；当前仍是可复用视觉 scaffold，不得称 1:1。
3. 增加无障碍描述/语义、按压/焦点状态与自动化测试。
4. 完成依赖与许可审查、独立库 API 示例及发布构建配置。
5. 后续续作前先完整读取本文件，并追加 K.011，不覆盖旧历史。


### K.011 — 2026-10-26：建立统一页面骨架与多层设置详情展示

**用户要求**
- 主页可以为空，但顶部总标题栏、页面选项框架和底部导航栏必须保留。
- 功能页、状态栏页遵循同一页面骨架。
- 设置页需要承载 Voyage 设置视觉体系：分组、一级/二级/三级及更深层选项、详情、选项卡，并且所有层级都能应用统一主题组件。

**本次执行**
- 将 Showcase 改为固定全局 AlohaSkin 标题栏、页面选项框架、独立滚动内容区和固定底部浮动导航的统一骨架。
- 主页改为空白/极简页面，仍保留全局标题、页面选项框架和底部导航。
- 功能页与状态栏页沿用统一页面骨架，原有组件样例和静态状态栏预览保留。
- 设置页增加八个可展开分组，顺序遵循已观察的 Voyage 主题页面结构：DESIGN、NAVIGATION_BAR、MODE、LAUNCH、ASSIST、SHAPE、COMPONENTS、SHADOWS。
- 设置行可打开详情页面；详情页有“选项/样式/详情”选项卡，演示一级、二级、三级和更深层嵌套项，以及本地开关、滑块、选项 chips 和主题色/表面预览。
- 设置选项仍是本地视觉演示状态。未移植 Voyage 的设置持久化、业务导航、服务、权限、数据模型或系统副作用。
- 更新实现状态与组件清单，明确分组标题有参考证据，但子选项仍是代表性样例，不能冒充 Voyage 完整原文或宣称 1:1。

**提交与构建**
- 主代码提交：89b39755e9501f35e9c5548c35f546cfb71fb35e（feat: add shared page shell and nested theme settings showcase）。
- 实现状态与组件清单文档随后提交；维护记录提交会触发额外 CI。需核验与最新代码提交对应的 Actions 最终状态及 artifact。
- 本条记录生成前，前一轮文档提交对应 Run 40325834334 已完成且成功，Artifact ID 12651227498；但该产物不包含本次新页面骨架/嵌套设置代码，不能作为本轮最新 APK 交付。
- 工作分支仍为 feature/voyage-visual-system-extraction-20261026；main 未修改、未合并。

**未完成与下一步**
1. 检查本次代码提交和文档/记录提交触发的 CI；若失败，读取日志、修复并重跑，成功后下载匹配当前代码 SHA 的 APK 并验证 ZIP/APK。
2. 让页面选项框架支持交互切换，并补充无障碍语义/选中态。
3. 从 Voyage 源码/资源/截图继续补齐设置子选项的准确标签、顺序、层级和详情内容；当前子选项是视觉样例，不是完整转录。
4. 验证所有页面和嵌套层级的浅色/深色/动态主题效果，检查系统栏 Insets 和小屏滚动。
5. 继续完成尺寸、颜色、字体、形状、描边、阴影、动效测量和截图对照；不得在验收前称为 1:1。
6. 保持测试分支，不修改/合并 main。


### K.012 — 2026-10-26：补齐页面选项框架交互并跟踪最新构建

**本次修正**
- 根据 K.011 后续检查，把顶部“页面”选项框架从纯展示状态改为可点击切换四个主页面；底部浮动导航继续保留，两处入口共用同一个本地选中页面状态。
- 为顶部页面选项提供可见的选中/未选中主题色，仍使用 MaterialTheme 语义色，不硬编码页面容器颜色。
- 该修改只影响 Showcase 的页面切换表现，不引入 Voyage 业务导航。

**提交与 CI**
- 页面选项框架交互提交：46860f97b75a19a64c982c13e77479c4932770f9（feat: make page option frame interactive）。
- 主要页面骨架与多层设置展示提交：89b39755e9501f35e9c5548c35f546cfb71fb35e 的 CI Run 40326791944 已完成且成功；因此统一外壳/嵌套设置主实现已通过一次 CI 构建。
- 包含顶部选项交互的最新提交 Run 40327023145 在本条记录写入时仍为 in_progress，Build library and showcase 步骤尚未结束；不可将其状态推断为成功，也暂不交付旧 APK 作为最新版本。
- 文档提交 Runs 40326979227、40326985594、40326992486 在本条写入时也仍在运行。
- 工作分支仍为 feature/voyage-visual-system-extraction-20261026；main 未修改、未合并。

**未完成与下一步**
1. 继续检查 Run 40327023145 的最终结果；若失败，读取完整 Gradle 日志、修复并重跑。
2. 最新代码构建成功后下载匹配 SHA 的 Showcase APK，核验 artifact、ZIP 完整性和 APK 文件结构，再提供交付链接。
3. 继续补齐 Voyage 设置页面的真实子选项名称/顺序/层级与详情选项卡；当前仅八个分组标题有已记录的参考证据，子项仍是代表性视觉样例。
4. 检查顶部页面选项与底部导航的无障碍语义、选中状态、屏幕 Insets 和小屏布局。
5. 继续做浅/深/动态主题的逐层截图对照与尺寸/颜色/字体/形状/动效测量；验收前不宣称 1:1。


### K.013 — 2026-10-26：推进 Voyage 主题引擎的种子配色与系统对比度

**本次目标**
继续向 Voyage 已观察到的 Material 3 + MaterialKolor 主题路径推进；本轮先补主题模式、种子色调色板、调色板风格与系统对比度，而不是把临时色值误报为已测量的原版值。

**代码改动**
- 新增依赖 `com.materialkolor:material-kolor:2.0.0`，用于从种子色生成 Material 3 主题方案。依赖公开说明为 MIT；其内含的 Material Color Utilities 另有 Apache-2.0 许可，最终依赖/许可审计仍需完成。
- `AlohaSkinTheme` 新增 `SkinThemeMode.SYSTEM / LIGHT / DARK`；保留可选的旧 `darkTheme` 覆盖参数以兼容现有调用。
- 主题方案优先级明确为：Android 12+ 系统动态配色开关 → 自定义种子色 + MaterialKolor → 临时静态回退色板。
- 种子色调色板支持 Tonal Spot、Expressive、Vibrant、Monochrome；设置页加入 Violet、Ocean、Rose、Forest 四个本地预览种子。
- Android 14+ 尝试读取 `UiModeManager.contrast` 并传入 MaterialKolor；设置页可在系统对比度与手动 -1..+1 对比度之间切换。
- 设置页所有模式、种子色、风格和对比度状态仅保存在当前 Showcase 内存中，不接入 Voyage 持久化或业务行为。
- 更新 `docs/IMPLEMENTATION_STATUS.md`、`docs/COMPONENT_INVENTORY.md` 和 `docs/VISUAL_EVIDENCE.md`，把已实现路径和未核验的 Voyage 默认值分开记录。

**本轮提交**
- `1bb72935e2e3a4e985cd8cbbfefc675742865b22` — `feat: add MaterialKolor seed palette engine`
- `a4ae1baac4ddf30637629babe320712e3efc46c3` — `feat: add theme modes seed palettes and contrast adaptation`
- `434cd11fa64d0caea91d701a5a1ce30f64f32e28` — `feat: expose theme modes seed styles and contrast controls`
- 文档提交：`d7d43899064552a8287549698e05cad1df32cb66`、`1abe4649aaf0e2f5eec214b5eb3a5e3c7b6d9cf1`、`48739d04c2b9244ecce08b543bba192b4c88e3ed`。
- 分支仍为 `feature/voyage-visual-system-extraction-20261026`；`main` 未修改/未合并。

**构建状态**
- 最新 Showcase 代码提交 `434cd11fa64d0caea91d701a5a1ce30f64f32e28` 对应 Run `40327864773`；本条记录开始写入时仍在运行。
- 依赖提交 Run `40327784290` 与主题 API 提交 Run `40327807995` 当时也仍在运行，尚无最终结论。不能把早期页面框架构建成功视为本轮主题引擎已构建成功。
- 最近已明确成功的前一阶段提交 `46860f97b75a19a64c982c13e77479c4932770f9` 对应 Run `40327023145`，结论 success，Artifact ID `12652031370`，ZIP 大小 `8,399,504 bytes`，SHA-256 `a7c1e240e20a94da358e12581a435f98b98f2916ea0bdc2f0d93f155c5d1270d`。该产物不含本条新增的 MaterialKolor 主题引擎，不能作为本轮最新 APK。
- 本维护记录提交也会触发 CI；需继续检查最新 SHA 的最终构建和上传步骤，再决定是否交付 APK。

**准确性边界与未完成项**
1. 仍需验证当前 MaterialKolor 2.0.0 API 与项目 Compose/Kotlin 版本的实际编译兼容性；CI 若失败，读取完整 Gradle 日志并修复。
2. 当前只实现种子色生成与系统对比度输入，尚未实现完整 Material 3 ColorScheme 的动画过渡；需先确认所用依赖的实际 API，再按 Voyage 源证据实现，不能随意猜测时长/曲线。
3. Voyage 的准确 MaterialKolor 版本、PaletteStyle、默认种子、对比度读取/映射及过渡参数仍待回到反编译主题调用点核验。
4. 静态回退色板、shape/spacing/stroke/elevation 令牌仍为 provisional；需要原版截图和源码定位逐项测量。
5. 设置页八个分组的子项标签/层级目前仍有代表性样例，尚不是完整逐项转录。
6. 完成浅/深色截图、交互/无障碍、依赖许可审计和最终构建验证后，才可讨论合并；此前保持测试分支。



### K.014 — 2026-10-26：修复 MaterialKolor API 参数并等待重建验证

**失败诊断**
- 主题模式/对比度提交 `a4ae1baac4ddf30637629babe320712e3efc46c3` 的 Run `40327807995` 失败。
- 读取完整 Actions job log 后确认：MaterialKolor 2.0.0 的 `rememberDynamicColorScheme` 重载要求显式提供 `isAmoled`，当前调用未提供该必需参数，导致 `SkinTheme.kt:81` Kotlin 编译失败。
- Showcase 主题控制提交 `434cd11fa64d0caea91d701a5a1ce30f64f32e28` 的 Run `40327864773` 同样因该问题失败，未产生可交付 APK。

**修复**
- 在 MaterialKolor 种子色方案调用中显式加入 `isAmoled = false`，避免将 AMOLED 黑底误作 Voyage 的默认主题行为。
- 修复提交：`3eeaeea177b296a6a565995ab9f93e51820a40bb`（`fix: provide MaterialKolor AMOLED argument`）。
- 依赖单独加入的提交 `1bb72935e2e3a4e985cd8cbbfefc675742865b22` 对应 Run `40327784290` 已成功，说明 MaterialKolor 2.0.0 依赖可解析；但主题 Kotlin 编译仍须以修复后的 Run 为准。

**当前构建状态**
- 修复提交对应 Run `40328131318`：`https://github.com/iskyxpcity/AlohaSkin/actions/runs/40328131318`，本条写入时仍为 `in_progress`。
- 不宣称修复后的主题已构建成功；不交付前一阶段 APK 作为最新主题版。
- 最近成功且可验证的前一阶段仍是页面框架提交 `46860f97b75a19a64c982c13e77479c4932770f9` 的 Run `40327023145`，Artifact ID `12652031370`；该 APK 不包含本轮 MaterialKolor 种子配色与对比度功能。
- 工作分支仍为 `feature/voyage-visual-system-extraction-20261026`；`main` 未修改/未合并。

**后续**
1. 检查 Run `40328131318` 的最终构建结果；若失败继续读取完整日志、修复并重跑。
2. 最新代码成功后下载匹配 SHA 的 APK，核验 ZIP 完整性、内部 APK、文件大小和 SHA-256。
3. 继续确认 MaterialKolor 的完整配色动画 API，并从 Voyage 主题调用点提取实际 style、seed、contrast 与动画规格。
4. 继续逐项校准主题组件和设置层级，保持未测量参数为 provisional；不得宣称 1:1 已完成。


### K.015 — 2026-10-26：将调色板风格类型收敛为库内公开 API

**第二轮构建诊断**
- 修复 `isAmoled` 后，Run `40328131318` 进入 Showcase 编译并再次失败。
- 完整日志显示 `MainActivity.kt` 无法解析 `com.materialkolor.PaletteStyle`，原因是 MaterialKolor 仅作为 `aloha-skin` 的 implementation 依赖，未暴露给 Showcase；这也是组件库 API 不应要求调用方直接依赖实现库的设计问题。

**修复**
- 在 `SkinTheme.kt` 增加库自身的 `SkinPaletteStyle` 枚举：`TONAL_SPOT`、`EXPRESSIVE`、`VIBRANT`、`MONOCHROME`。
- 由 AlohaSkin 内部把 `SkinPaletteStyle` 映射到 MaterialKolor 的 `PaletteStyle`，保持 MaterialKolor 为实现细节，避免 Showcase/下游应用直接依赖它。
- Showcase 已切换到 `SkinPaletteStyle`，不再导入 MaterialKolor 类型。
- 修复提交：`022d3e8f8d47af6ae74d0eaf6359b472cd836f9e`（`refactor: expose library-owned palette style API`）；`0d88e6d1f3eb59faf85fad2a1358a8d8f6d0cb2b`（`fix: use library palette style API in showcase`）。

**构建状态**
- 本条记录写入时，提交 `0d88e6d1f3eb59faf85fad2a1358a8d8f6d0cb2b` 对应 Run `40328459924` 仍处于 `in_progress`；前一个库 API 提交 Run `40328446504` 也仍在运行。
- 修复前 Run `40328131318` 失败，因此没有生成匹配当前主题代码的 APK。必须等最新修复的 build 和 Upload showcase APK 都成功后再交付。
- `main` 未修改/未合并；测试分支不变。

**下一步**
1. 核对 Run `40328459924` 最终结果；若仍失败，读取完整日志并继续修复。
2. 成功后下载与最新代码 SHA 匹配的 artifact，检查 ZIP/APK 和 SHA-256。
3. 继续核对 MaterialKolor 的动画 API 与 Voyage 原版调用点；实现完整 ColorScheme 动画前先确认版本/参数，不猜时长或曲线。
4. 继续补齐 Voyage 设置页子选项的真实标签/层级，测量浅/深色截图与组件状态；目前仍未完成 1:1。


### K.016 — 2026-10-26：完成种子色方案动画接入并验证阶段性 APK

**本轮工作**
- 先核对最新维护记录 K.015 和 GitHub Actions 结果，没有修改或合并 `main`。
- 已确认提交 `0d88e6d1f3eb59faf85fad2a1358a8d8f6d0cb2b` 的 Run `40328459924` 成功，library/showcase build 与 APK 上传步骤均成功。
- 下载该 run 的 Artifact ID `12652476924`，ZIP 大小 `8,660,937 bytes`，Artifact SHA-256 为 `af75ef83b205c3535e1084254c641f4848d5dd253cd2c760e726925cf8870fec`。ZIP 内包含 `showcase-debug.apk`，大小 `9,067,471 bytes`；ZIP 测试通过，APK 自身 ZIP 完整性检查通过。提取 APK 的 SHA-256：`eb4f1dc9deadecdaa6ae950d5716a30c0a0ea090801f0211ae13dd336324b1da`。该 APK 是可安装的阶段性 Showcase 构建，但不包含本节新增的动画改动，不能冒充动画版最终产物。
- 根据 MaterialKolor 2.0.0 的版本文档，使用库提供的 `DynamicMaterialTheme(animate = true)` 为自定义种子色路径接入完整 Material 3 ColorScheme 动画，并传入 AlohaSkin 的共享 shape tokens。当前使用 MaterialKolor 默认 spring；Voyage 的准确动画时长/曲线仍未证实，因此不声称动效像素级一致。
- 当 Android 12+ 原生系统动态配色被显式启用时，仍走系统动态配色分支；该分支暂未套用自定义动画包装。
- 更新 `docs/IMPLEMENTATION_STATUS.md` 与 `docs/VISUAL_EVIDENCE.md`，记录动画覆盖范围、未验证边界及依赖 API 证据。

**新增代码与文档提交**
- 代码：`b8f0a3785bd1ef23b6244fd659b689dee3a89b8e` — `feat: animate generated seed theme transitions`。
- 状态文档：`0041a30a4b55248a24bbacb38bd1926e035a948b` — `docs: track seed theme animation implementation`。
- 视觉证据：`f5cb9b2b23e2851d386613bd3d5cc73c16d6ec6b` — `docs: record seed palette animation evidence`。
- 本维护记录追加后另行提交。分支仍为 `feature/voyage-visual-system-extraction-20261026`；`main` 未修改/未合并。

**当前构建状态**
- 动画代码提交 Run `40329133639` 在写入本条时为 `in_progress`。
- 文档提交 Run `40329168534` 在写入本条时为 `in_progress`。
- 以上两个 run 尚未有最终结论；必须继续检查最新 HEAD 对应的 build 与 artifact upload，失败时读取日志修复并重建。
- 已成功并完成完整性核验的阶段性 APK 下载文件：`/mnt/data/AlohaSkin_Showcase_Debug_20261026.apk`。APK SHA-256：`eb4f1dc9deadecdaa6ae950d5716a30c0a0ea090801f0211ae13dd336324b1da`。它对应 SHA `0d88e6d1...`，不是本轮动画代码 SHA。

**仍未完成，不能宣称 1:1**
1. 等待并验证动画代码最新 SHA 对应的 CI 及 APK artifact；如失败需修复到成功。
2. 完整核对 Voyage 主题调用点中的 MaterialKolor 版本、默认 seed/style、contrast 映射和动画规格；现有动画只实现了机制，未确认与原版参数相同。
3. 补齐八个设置分组下的真实子项标签、顺序和深层页面结构；目前仍有代表性样例。
4. 对浅色/深色参考图逐组件测量页面 inset、行高、字体、圆角、outline、elevation、选中/禁用状态和动效偏差。
5. 完成自动化/无障碍测试及依赖许可审计，确认最终可交付包后再评估合并；目前禁止合并 `main`。

**下次续作**
- 先检查 Run `40329133639` 与 `40329168534`，以最新代码 SHA 为准；修复任何构建错误，下载并校验对应 APK，再继续视觉证据校准与许可/测试收尾。
