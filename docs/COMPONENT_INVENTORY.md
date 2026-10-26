# Visual Component Inventory

No component is marked complete until its appearance and states have been implemented and compared against reference evidence.

## Shared app shell

| Area | Required behavior | Status |
|---|---|---|
| Global title bar | Persistent project title above each page | Implemented; sizing/typography not measured against Voyage |
| Page-option frame | Four-page selector and selected state | Clickable; focus/semantics and exact styling pending |
| Bottom navigation | Four destinations; selected/unselected visuals | Implemented with local state |
| Scroll container | Content scrolls independently from persistent shell | Implemented; real-device inset testing pending |
| Theme propagation | Shared semantic colors and shapes reach all pages/nested levels | Implemented; visual parity pending |

## Theme engine

| Capability | Status |
|---|---|
| System/light/dark mode | Implemented using system mode plus explicit light/dark overrides |
| Android 12+ system dynamic palette | Native Material 3 dynamic color path implemented |
| Custom seed palette | MaterialKolor seed generation wired into shared theme |
| Palette styles | Tonal Spot, Expressive, Vibrant and Monochrome exposed in Showcase |
| Seed-color choices | Violet, Ocean, Rose and Forest preview seeds |
| Android 14+ contrast | Reads system contrast when available; manual -1..+1 preview control also available |
| Full color-scheme animation | Implemented for the custom seed path using MaterialKolor 2.0.0 `DynamicMaterialTheme(animate = true)`; exact Voyage timing/easing pending |
| Voyage-exact seed/style/contrast defaults | Pending source-call-site verification |
| Static fallback palette | Provisional only; no pixel-parity claim |
| Shared shape/spacing/stroke/elevation tokens | Centralized but values still provisional |

## Four primary pages

| Page | Expected composition | Status |
|---|---|---|
| Home | May be empty; retain global title, page frame and bottom navigation | Intentionally sparse |
| Features | Reusable controls, frames/surfaces, palette, tabs, dialog and sheet | Initial samples implemented |
| Status bar | Themeable mock status-bar visual and component options | Static mock; no system side effects |
| Settings | Theme modes, expandable groups, nested details, tabs and themed controls | Theme engine controls implemented; detailed labels need source verification |

## Settings reference composition

Observed Voyage theme page expandable groups in this order: DESIGN, NAVIGATION_BAR, MODE, LAUNCH, ASSIST, SHAPE, COMPONENTS, SHADOWS.

The Showcase uses these group labels with representative child rows to exercise nesting. Child option labels are not certified as a complete transcription of Voyage. No Voyage setting persistence or business behavior is imported.

## Reusable controls

| Component family | Required states/variants | Status |
|---|---|---|
| Button | Selected/unselected, pressed, disabled, filled/outlined/tonal where evidenced | Initial states |
| Switch | On/off, enabled/disabled | Implemented |
| Slider | Track, thumb, active/inactive, enabled/disabled | Implemented |
| Preference row | Leading icon, title, subtitle, trailing control/arrow | Implemented; layout measurement pending |
| Expandable group | Collapsed/expanded, arrow rotation and nested spacing | Implemented; timing/geometry pending |
| Tabs | Selected/unselected indicator and text | Implemented; geometry pending |
| Chips/selectors | Selected/unselected and disabled | Implemented; geometry pending |
| Dialog | Surface, title/body hierarchy, actions | Generic shell implemented |
| Bottom sheet | Shape, handle, surface and insets | Generic shell implemented |
| Palette card | Swatches, selection outline and preview | Implemented; exact treatment pending |
| Floating navigation | Surface, selection and icon/label layout | Implemented; translucency/dimensions pending |

## Evidence required for completion

- Source class/resource references or reference screenshot
- Light and dark screenshots
- Measured margins, padding, heights, radii, stroke, icon/text sizes
- Semantic color roles and state mappings
- All meaningful visual states
- Implementation path/public API, tests and deviations
