# Implementation Status

## Current implementation pass

The repository contains a standalone Android Compose library module and a demo showcase app.

### Components implemented (visual parity not yet certified)

- Semantic light/dark color schemes (provisional fallback values)
- Optional Android 12+ system dynamic Material 3 palette (opt-in and switchable in Showcase)
- Shared provisional design tokens: spacing, radii, stroke widths and elevation levels in `SkinTokens.kt`
- Shared Material 3 shape scale wired through `AlohaSkinTheme`
- Outlined rounded frame/container: `SkinFrame`
- Generic surface/container primitive: `SkinSurface`
- Expandable group with size animation and rotating disclosure glyph: `SkinAccordion`
- Preference row with optional leading and trailing slots: `SkinPreferenceRow`
- Switch row: `SkinSwitchRow`
- Selected/unselected action button: `SkinActionButton`
- Choice chips: `SkinChoiceChips`
- Slider row: `SkinSliderRow`
- Tabs: `SkinTabs`
- Theme palette preview card: `SkinPaletteCard`
- Enabled/disabled primary and outlined button pair: `SkinButtonPair`
- Generic confirmation dialog shell: `SkinDialog`
- Generic modal sheet shell: `SkinBottomSheet`
- Floating navigation bar with selected/unselected items and optional translucent surface: `SkinFloatingNavigationBar`
- Showcase demonstrates light/dark and dynamic palette switches, tabs, palette cards, switch/chip/slider states, buttons, dialog and sheet with local-only demo state.

### Still pending

- Extract and verify actual color, typography, spacing, shape, outline and elevation values from Voyage resources/screenshots
- Custom seed/palette generation, Android 14+ contrast adaptation and animated scheme transitions; dependency and source-call-site review required
- Replace provisional theme values only when supported by evidence
- Exact state mapping and light/dark visual comparison for every component, including floating navigation
- Apply shared dimension tokens consistently to all components; existing component-level literals have not all been normalized yet
- Choice chips and action buttons accept explicit disabled state; verify visual states and add accessibility semantics
- More control variants, pressed/focused/disabled states and accessibility semantics
- Calibrate floating/glass surface opacity, stroke, radius, blur and elevation from source evidence/screenshots
- Reference screenshots and documented deviation list
- Verify latest CI run and archive the matching successful showcase APK
- API usage examples, dependency/license review and tests

### Accuracy note

The current implementation is a reusable scaffold, not a claim of pixel-perfect Voyage parity. Measurements remain explicitly pending in `docs/VISUAL_EVIDENCE.md`. Token values are centralized but remain provisional. Demo controls intentionally do not trigger Voyage settings or system behavior.
