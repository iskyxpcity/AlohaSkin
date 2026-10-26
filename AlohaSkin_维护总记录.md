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

