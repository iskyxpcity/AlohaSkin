# Visual Component Inventory

No component is marked complete until its appearance and states have been implemented and compared against reference evidence.

## Shared app shell

| Area | Required behavior | Status |
|---|---|---|
| Global title bar | Persistent project title above each page | Implemented; sizing/typography not measured against Voyage |
| Page-option frame | Themed selector/overview for four primary pages | Current frame displays selected page; interactive selection still pending |
| Bottom navigation | Four primary destinations; selected/unselected visuals | Implemented with local Showcase navigation |
| Scroll container | Page content scrolls independently from persistent shell | Implemented; real-device inset testing pending |
| Theme propagation | Light/dark/dynamic Material scheme reaches every page and nested component | Implemented via shared MaterialTheme; exact Voyage palette mapping pending |

## Theme and surface system

| Area | Observed visual responsibility | Status |
|---|---|---|
| Light/dark theme | Semantic colors, foreground/background and surface contrast | Initial tokens implemented; exact resource mapping pending |
| Dynamic palette | Accent/palette roles and theme-scheme presentation | Native dynamic palette preview scaffolded; exact mapping pending |
| Surface/container | Container fills, corner radius, insets and elevation | SkinSurface scaffolded; measurements pending |
| Outlined frame | Border/stroke, shape, internal spacing and nesting | SkinFrame scaffolded; measurements pending |
| Typography | Title, body, supporting label, disabled and emphasized text | Material defaults; Voyage style mapping pending |
| Icons | Size, alignment, tint and selected-state treatment | Audit pending |
| Shape system | Shape family and corner-size variants | Rounded shapes scaffolded; exact variants pending |
| Shadows/elevation | Separate surface/elevation recipes | Elevation currently neutral; source mapping pending |
| Motion | Expand/collapse rotation, selection and transition timing/curves | Basic expand animation scaffolded; exact timing pending |

## Four primary pages

| Page | Expected composition | Status |
|---|---|---|
| Home | May be empty; retain global title, page option frame and bottom navigation | Intentionally sparse |
| Features | Reusable controls, frames/surfaces, palette, tabs, dialog and sheet | Initial samples implemented |
| Status bar | Themeable mock status-bar visual and component options | Static mock; does not affect system UI |
| Settings | Theme options, expandable groups, nested details, tabs and themed controls | Hierarchy scaffold implemented; option labels/content need source verification |

## Settings reference composition

Observed Voyage theme page expandable groups in this order:

1. DESIGN
2. NAVIGATION_BAR
3. MODE
4. LAUNCH
5. ASSIST
6. SHAPE
7. COMPONENTS
8. SHADOWS

The Showcase currently uses these group labels and representative child rows to exercise expandable, nested settings UI. Child option labels are not yet certified as a complete transcription of Voyage. No Voyage setting behavior or persistence is imported.

## Reusable controls

| Component family | Required states/variants | Status |
|---|---|---|
| Button | Selected/unselected, pressed, disabled, tonal/outlined/filled variants where evidenced | Initial selected/action and enabled/disabled button pair |
| Switch | On/off, enabled/disabled | Material switch row scaffolded |
| Slider | Track, thumb, active/inactive segments, enabled/disabled | Slider row scaffolded |
| Preference/settings row | Leading icon, title, subtitle, trailing control/arrow | Leading/trailing slots implemented; layout pending |
| Expandable group | Collapsed/expanded, arrow rotation, nested content spacing | Nested levels scaffolded; exact geometry pending |
| Tabs | Selected/unselected indicator and text treatment | SkinTabs scaffolded and used in detail pages |
| Chips/selectors | Selected/unselected and disabled | Choice chips scaffolded |
| Dialogs | Surface, title/body hierarchy, actions | SkinDialog scaffolded |
| Bottom sheets | Shape, handle, surface and insets | SkinBottomSheet scaffolded |
| Theme palette/scheme cards | Preview swatches, selection outline and editor presentation | SkinPaletteCard scaffolded |
| Navigation surface | Floating bar and glass-like surface where supported | Dimensions/translucency/state style pending visual verification |

## Required evidence for every completed item

- Source class/resource references or screenshot reference
- Light and dark screenshots
- Dimensions: outer margins, padding, height, radius, stroke, icon/text sizes
- Semantic color tokens used
- All meaningful visual states
- Implementation path and public API
- Verification notes and known deviations
