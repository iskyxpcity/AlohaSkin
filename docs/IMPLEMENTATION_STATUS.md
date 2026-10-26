# Implementation Status

## Current implementation pass

The repository contains a standalone Android Compose library module and a Showcase app with four primary navigation pages.

### Four primary navigation pages

- **主页** — project overview, visual palette preview and shortcuts to Features/Settings.
- **功能** — grouped reusable controls, surface patterns, palette cards, dialog and bottom-sheet samples.
- **状态栏** — static status-bar mock preview with local toggles for clock, network speed, Wi-Fi, mobile signal and battery.
- **设置** — local light/dark preview, Android 12+ system dynamic-color switch and implementation notes.
- The four-item floating navigation stays visible at the bottom while page content scrolls. Page selection is local Showcase state only; no business routes or persistent settings are imported.
- Status-bar mock values are illustrative only and do not read device telemetry or modify Android's real status bar.

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

### Build verification

- Four-page Showcase code commit: `ac1df402f6ab12bdecf3f0a9075d00444839d10c`.
- The CI run for this commit must complete successfully before this version is called a verified build or its APK is delivered.
- Previously verified Debug Showcase artifact was built from commit `ca7fc5e73b010649e809eef81955a86365d2c908`; it does **not** include the four-page navigation update and must not be represented as the new version.

### Still pending

- Confirm the latest CI run for the four-page navigation commit and archive its matching APK
- Extract and verify actual color, typography, spacing, shape, outline and elevation values from Voyage resources/screenshots
- Custom seed/palette generation, Android 14+ contrast adaptation and animated scheme transitions; dependency and source-call-site review required
- Replace provisional theme values only when supported by evidence
- Exact state mapping and light/dark visual comparison for every component, including floating navigation
- Apply shared dimension tokens consistently to all components; component-level literals have not all been normalized yet
- Verify choice chips/action buttons disabled-state appearance and add accessibility semantics
- More control variants, pressed/focused/disabled states and accessibility semantics
- Calibrate floating/glass surface opacity, stroke, radius, blur and elevation from source evidence/screenshots
- Reference screenshots and documented deviation list
- API usage examples, dependency/license review and automated tests

### Accuracy note

The current implementation is a reusable scaffold, not a claim of pixel-perfect Voyage parity. Measurements remain explicitly pending in `docs/VISUAL_EVIDENCE.md`. Token values are centralized but remain provisional. Demo controls intentionally do not trigger Voyage settings or system behavior.
