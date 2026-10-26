# Visual Component Inventory

This is the initial evidence-based inventory. A component is not marked complete until its style and states have been implemented and compared against reference screenshots.

## Theme and surface system

| Area | Observed visual responsibility | Status |
|---|---|---|
| Light/dark theme | Semantic colors, foreground/background and surface contrast | Inventory started |
| Dynamic palette | Accent/palette roles and theme-scheme presentation | Inventory started |
| Surface/container | Container fills, corner radius, insets and elevation | Needs detailed measurement |
| Outlined frame | Border/stroke, shape, internal spacing and nesting | Needs detailed measurement |
| Typography | Title, body, supporting label, disabled and emphasized text | Needs resource/style mapping |
| Icons | Size, alignment, tint and selected-state treatment | Needs asset/license audit |
| Shape system | Shape family and corner-size variants | Inventory started |
| Shadows/elevation | Separate surface/elevation recipes | Inventory started |
| Motion | Expand/collapse rotation, selection and transition timing/curves | Needs measurement |

## Reusable controls

| Component family | Required states/variants | Status |
|---|---|---|
| Button | Selected/unselected, pressed, disabled, tonal/outlined/filled variants where evidenced | Needs implementation |
| Switch | On/off, enabled/disabled | Needs implementation |
| Slider | Track, thumb, active/inactive segments, enabled/disabled | Needs implementation |
| Preference/settings row | Leading icon, title, subtitle, trailing control/arrow | Observed; needs full state mapping |
| Expandable group | Collapsed/expanded, arrow rotation, nested content spacing | Observed; needs detailed verification |
| Tabs | Selected/unselected indicator and text treatment | Needs implementation |
| Chips/selectors | Selected/unselected and disabled | Needs evidence mapping |
| Dialogs | Surface, title/body hierarchy, actions | Needs evidence mapping |
| Bottom sheets | Shape, handle, surface and insets | Needs evidence mapping |
| Theme palette/scheme cards | Preview swatches, selection outline and editor presentation | Observed; needs detailed verification |
| Navigation surface | Floating bar and glass-like surface appearance where supported | Observed; platform support to be documented |

## Known theme-settings composition reference

The Voyage theme page presents these eight expandable groups in order:

1. DESIGN
2. NAVIGATION_BAR
3. MODE
4. LAUNCH
5. ASSIST
6. SHAPE
7. COMPONENTS
8. SHADOWS

This list is used only to inventory the visual patterns and sample controls. AlohaSkin must not implement the associated Voyage product settings or their persistence/side effects.

## Required evidence for every completed item

- Source class/resource references or screenshot reference
- Light and dark screenshots
- Dimensions: outer margins, padding, height, radius, stroke, icon/text sizes
- Semantic color tokens used
- All meaningful visual states
- Implementation path and public API
- Verification notes and known deviations
