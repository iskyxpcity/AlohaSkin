# Visual Component Inventory

No component is marked complete until its appearance and states have been implemented and compared against reference evidence.

## Theme and surface system

| Area | Observed visual responsibility | Status |
|---|---|---|
| Light/dark theme | Semantic colors, foreground/background and surface contrast | Initial tokens implemented; exact resource mapping pending |
| Dynamic palette | Accent/palette roles and theme-scheme presentation | Palette preview component scaffolded; exact mapping pending |
| Surface/container | Container fills, corner radius, insets and elevation | `SkinSurface` scaffolded; measurements pending |
| Outlined frame | Border/stroke, shape, internal spacing and nesting | `SkinFrame` scaffolded; measurements pending |
| Typography | Title, body, supporting label, disabled and emphasized text | Uses Material defaults; Voyage style mapping pending |
| Icons | Size, alignment, tint and selected-state treatment | Audit pending |
| Shape system | Shape family and corner-size variants | Rounded shapes scaffolded; exact variants pending |
| Shadows/elevation | Separate surface/elevation recipes | Elevation currently neutral; source mapping pending |
| Motion | Expand/collapse rotation, selection and transition timing/curves | Basic expand animation scaffolded; exact timing pending |

## Reusable controls

| Component family | Required states/variants | Status |
|---|---|---|
| Button | Selected/unselected, pressed, disabled, tonal/outlined/filled variants where evidenced | Initial selected/action and enabled/disabled button pair |
| Switch | On/off, enabled/disabled | Material switch row scaffolded; state visual verification pending |
| Slider | Track, thumb, active/inactive segments, enabled/disabled | Slider row scaffolded; state visual verification pending |
| Preference/settings row | Leading icon, title, subtitle, trailing control/arrow | Leading/trailing slots implemented; exact layout pending |
| Expandable group | Collapsed/expanded, arrow rotation, nested content spacing | Initial implementation present; exact visual match pending |
| Tabs | Selected/unselected indicator and text treatment | `SkinTabs` scaffolded |
| Chips/selectors | Selected/unselected and disabled | Choice chips scaffolded; disabled state pending |
| Dialogs | Surface, title/body hierarchy, actions | `SkinDialog` scaffolded and shown in demo |
| Bottom sheets | Shape, handle, surface and insets | `SkinBottomSheet` scaffolded and shown in demo |
| Theme palette/scheme cards | Preview swatches, selection outline and editor presentation | `SkinPaletteCard` scaffolded |
| Navigation surface | Floating bar and glass-like surface appearance where supported | `SkinFloatingNavigationBar` scaffolded; dimensions, translucency and state style pending visual verification |

## Known theme-settings composition reference

Voyage theme page expandable groups are ordered:

1. DESIGN
2. NAVIGATION_BAR
3. MODE
4. LAUNCH
5. ASSIST
6. SHAPE
7. COMPONENTS
8. SHADOWS

These labels are sample showcase data only. AlohaSkin must not implement associated Voyage settings or persistence/side effects.

## Required evidence for every completed item

- Source class/resource references or screenshot reference
- Light and dark screenshots
- Dimensions: outer margins, padding, height, radius, stroke, icon/text sizes
- Semantic color tokens used
- All meaningful visual states
- Implementation path and public API
- Verification notes and known deviations
