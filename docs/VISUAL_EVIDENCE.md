# Voyage Visual Evidence Ledger

This ledger separates observed source facts from implementation assumptions. A row remains **unverified** until reference measurements or a screenshot comparison are attached.

## Source provenance

- Reference package: Voyage APK extracted for inspection.
- APK SHA-256: `b74d1f49c152e1fdd1af8fd1e36fdedebe283f3c1e740066f281857c0682c0b1`.
- Inspected decompiled classes include `ThemeSettingsRoute`, `ThemeSchemeEditorRoute`, `vfw0`, `eja0`, `dgw0`, `zcj0`, and `k9t0`.
- This ledger records appearance/layout observations only. It does not authorize reuse of code/assets and does not define Voyage feature behavior for AlohaSkin.

## Confirmed composition observations

| Reference | Observation | Evidence source | Implementation status |
|---|---|---|---|
| Theme settings page | Eight expandable sections in order: DESIGN, NAVIGATION_BAR, MODE, LAUNCH, ASSIST, SHAPE, COMPONENTS, SHADOWS | Decompiled theme route and UI helper | Showcase samples only; exact dimensions pending |
| Expandable group | Group row has a disclosure affordance and animated expanded content | Decompiled reusable UI helper `vfw0` | Initial `SkinAccordion`; animation timing and geometry pending |
| Theme scheme page | Theme/palette selection and scheme editing are presentational card/control patterns | Theme route and scheme editor route | Initial `SkinPaletteCard`; exact card treatment pending |
| Theme modes | Light, dark, and system modes are represented in the UI | Theme route and localized strings | Semantic light/dark tokens exist; system/dynamic palette mapping pending |
| Motion and feedback settings | Labels/options are present in original UI, but their device behavior is out of scope | Decompiled UI strings and settings route | No Voyage behavior is imported |

## Measurement worksheet

Record measurements only after extracting them from resource dimensions/styles or measuring a reference screenshot.

| Property | Reference value | Evidence locator | Status |
|---|---|---|---|
| Page horizontal inset | TBD | Resource/screenshot locator required | Pending |
| Group corner radius | TBD | Resource/screenshot locator required | Pending |
| Group row height | TBD | Resource/screenshot locator required | Pending |
| Group title font family/size/weight | TBD | Text style/resource locator required | Pending |
| Expanded content inset/gap | TBD | `vfw0` + screenshot measurement | Pending |
| Outline width/color | TBD | Shape/color resource locator required | Pending |
| Surface fill colors (light/dark) | TBD | Color resource/theme mapping required | Pending |
| Disclosure rotation and duration | Rotation behavior observed; duration TBD | `vfw0` animation code | Pending |
| Palette card dimensions and selected outline | TBD | Theme scheme editor + screenshot | Pending |
| Button/chip selected and disabled states | TBD | UI component class/style mapping | Pending |

## Rule

Do not replace TBD with guessed values. The initial Compose token values are scaffolding and must remain labelled provisional until verified.

## Resource audit added 2026-10-26

- `decompiled/resources/res/values/colors.xml` includes `glance_colorPrimary=#ff6750a4`, `glance_colorPrimaryContainer=#ffeaddff`, `glance_colorOnPrimaryContainer=#ff21005d`, `glance_colorSurface=#fffffbfe`, `glance_colorSurfaceVariant=#ffe7e0ec`, and `glance_colorOutline=#ff79747e`. These are explicitly named `glance_color*` resources, so they are evidence for the Glance/widget palette only; they must **not** be assumed to be the main Compose app theme without corroborating Compose theme evidence.
- The same resource archive contains numerous third-party/library colors and dimensions (AndroidX, Material, ads SDKs, widgets). Generic values such as `design_tab_text_size=14sp` or `glance_component_square_icon_button_corners=16dp` are library-scoped and not proof of Voyage's actual Compose settings component measurements.
- The APK includes dynamic Material theme code from MaterialKolor. This supports that the app has dynamic color-scheme infrastructure, but exact seed/style/contrast defaults must be extracted from the actual theme call site before porting values.
- Result: no main-app Compose spacing/radius/color values are certified from this resource pass yet. The current AlohaSkin values remain provisional. This avoids mistaking embedded dependency defaults for Voyage UI measurements.

## Expandable-group source inspection — 2026-10-26

- In decompiled `p000/vfw0.java`, method `m137940Oo` (around lines 768–920 in the extracted file) contains the expandable-group rendering path.
- The disclosure rotation target is explicitly `180.0f` when expanded and `0.0f` when collapsed. The exact animation spec is delegated to a helper and is not yet confirmed; do not guess duration/easing.
- The body/container path includes a helper invocation with `8.0f` (`fqr.lOo(wgzVar, 8.0f)`). Treat this as a candidate 8dp inset only after confirming the helper's meaning; do not yet apply it as a certified page-wide spacing token.
- Group colors are derived from the active theme scheme and use 0.5f color blending in this method. This supports a scheme-relative treatment, not one fixed universal RGB value.
- Implementation action: keep `SkinAccordion`'s rotation endpoints at 0°/180°; the currently selected easing/duration and container metrics remain provisional pending further call-site/helper inspection and screenshot comparison.

## Floating navigation / translucent surface — 2026-10-26

- The decompiled theme settings route exposes a navigation-bar section, and prior inspection recorded floating navigation and a Liquid Glass-style option as visual concepts in the Voyage UI. This supports including a reusable navigation-surface primitive in the component inventory.
- The exact shape, blur implementation, opacity, outline, elevation, item sizing and selected-state indicator have **not** yet been measured against a reference screenshot or confirmed from the actual Compose implementation. The current `SkinFloatingNavigationBar` uses a translucent surface, outline and selected container as a provisional visual scaffold only.
- No navigation destinations, route changes, settings persistence, or system behavior are imported. The host supplies each item's label, icon composable and click callback.
- Verification status: implementation scaffolded; parity unverified. Do not label as 1:1 until reference screenshots and measurements are attached.

## Dynamic theme pathway — 2026-10-26

- Prior APK static analysis recorded that Voyage uses Material 3 and MaterialKolor dynamic color infrastructure: the default dynamic path can use the Android system Material 3 scheme, while custom palette/seed paths use MaterialKolor; the analyzed path also reads system contrast on Android 14+ and animates color-scheme changes.
- AlohaSkin exposes an opt-in `dynamicColor` flag using Android's native dynamic Material 3 scheme on Android 12+; older versions and opt-out use the local fallback palette.
- The Showcase now has a switch to preview this dynamic palette branch. On older Android versions, enabling the switch intentionally falls back to the local scheme.
- This implements only the native dynamic palette branch. Custom seed/palette generation, contrast adaptation, and animated scheme transitions remain pending until their source call sites and dependency requirements are reviewed. The fallback RGB values remain provisional and are not certified as Voyage's palette.

## Shared design tokens — 2026-10-26

- Added `SkinTokens.kt` to centralize provisional spacing, radius, stroke and elevation values and to expose a shared Material 3 shape scale through `AlohaSkinTheme`.
- This is a maintainability step, **not** a source-derived measurement result. Existing component-level literal dimensions are not yet fully normalized to these tokens.
- The values in `SkinSpacing`, `SkinRadii`, `SkinStroke`, `SkinElevation` and `SkinShapes` remain provisional. No Voyage reference screenshot or source style has yet certified them.


## Theme engine expansion — 2026-10-26

- Added the MIT-licensed MaterialKolor dependency as the seed-based Material 3 palette generator. Upstream documents the seed-to-scheme pathway and palette styles; this establishes the implementation mechanism, not Voyage's exact version/defaults.
- AlohaSkin now supports SYSTEM/LIGHT/DARK appearance mode, native Android 12+ system dynamic colors, MaterialKolor seed palettes, four local seed choices and four palette styles.
- On Android 14+ the theme reads `UiModeManager.contrast` when available and forwards it to the MaterialKolor seed scheme. A manual contrast slider is exposed for controlled preview. On earlier Android versions, automatic contrast falls back to 0.0.
- Current theme mode, seed, palette style and contrast controls are local Showcase state. They do not persist settings or invoke Voyage functionality.
- The source analysis previously established that Voyage uses MaterialKolor, reads Android 14+ contrast and animates color scheme changes. Exact Voyage version, style/default seed, contrast mapping and transition specification still need confirmation. AlohaSkin's complete scheme animation is not implemented in this pass.
- Upstream implementation/API reference: MaterialKolor repository and its documented `rememberDynamicColorScheme` seed/style/contrast pathway. Dependency license is MIT; the library also incorporates Google's Apache-2.0 Material Color Utilities. License notices still need to be included in the final dependency audit.


## Seed-palette color-scheme animation — 2026-10-26

- Added the MaterialKolor 2.0.0 `DynamicMaterialTheme` path for custom seed palettes, with `animate = true` and the library-owned shared shape scale. This animates the full Material 3 color scheme rather than manually animating only a subset of colors.
- The path applies when a custom seed is active and Android native dynamic colors are not selected. If Android 12+ native system dynamic colors are explicitly enabled, that native scheme remains the selected path; it is not wrapped in a custom animation yet.
- The transition currently uses MaterialKolor's default spring specification. The analyzed Voyage code indicates animated scheme changes, but its exact animation spec has not yet been confirmed, so timing/easing parity remains unverified.
- API reference for the pinned 2.0.0 line documents `DynamicMaterialTheme(... animate = true)`: citeturn1search0 (reference captured during this maintenance pass; verify against the pinned artifact if the dependency version changes).
