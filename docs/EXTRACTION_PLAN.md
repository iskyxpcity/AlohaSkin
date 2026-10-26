# Voyage Visual-System Extraction Plan

## Non-negotiable boundary

Extract **appearance and reusable UI primitives only**. Do not port or expose Voyage feature behavior. Avoid importing its navigation routes, settings persistence, feature state, service integrations, permissions, account/data logic, or application-specific workflows.

## Evidence source

- Decompiled Voyage APK and resources available in the user's AppData repository at `voyage/`.
- Previously inspected decompiled classes include `ThemeSettingsRoute`, `ThemeSchemeEditorRoute`, and UI/theme helper classes `vfw0`, `eja0`, `dgw0`, `zcj0`, and `k9t0`.
- Known source APK SHA-256: `b74d1f49c152e1fdd1af8fd1e36fdedebe283f3c1e740066f281857c0682c0b1`.

These are reverse-engineering references, not source code available under an assumed open-source license. Reimplement the reusable visual system independently; only reuse assets/code when the license and provenance explicitly permit it.

## Extraction phases

1. **Inventory** — identify all theme tokens, UI primitives, reusable containers, frames, state styles, and supported variants from the decompiled resources and code.
2. **Token model** — define semantic colors, typography, spacing, shape, stroke, elevation, opacity, and motion tokens for light/dark themes.
3. **Primitive components** — implement base surfaces, outlined frames, text hierarchy, icons, buttons, switches, sliders, chips, tabs, list/preferences rows, selectors, dialogs, bottom sheets, and expandable groups.
4. **Composite patterns** — implement reusable settings-page layouts and theme-scheme editor visual patterns with placeholder/demo state only.
5. **Reference gallery** — build a component showcase with every component/state/variant, and capture reference screenshots at consistent dimensions.
6. **Visual verification** — compare each component to the Voyage reference in light and dark modes; track deviations explicitly rather than claiming parity without evidence.
7. **Packaging** — provide a reusable module/library API, theme tokens, usage documentation, and a demo app that contains no Voyage product features.

## Acceptance criteria

- All listed component families have a source-evidence note and a documented visual spec.
- Light and dark theme screenshots are available for each component family and key interaction state.
- Controls are visually interactive in the showcase, but showcase interactions do not implement Voyage features.
- No Voyage feature-specific services, navigation destinations, data models, permissions, or business logic are included.
- Build and tests pass for the standalone AlohaSkin module/demo.
- Repository README accurately distinguishes verified work from pending work.
