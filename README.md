# AlohaSkin

A reusable visual design system extracted from the observed UI of Voyage.

## Scope

AlohaSkin is a **visual-only component and theme library**. It captures the visual language, theme tokens, component shapes, surfaces, containers, frames, spacing, typography, states, and motion curves observed in Voyage. It does not port Voyage's product features, settings behavior, navigation logic, data models, services, permissions, or business code.

The implementation should be cleanly separated from Voyage's application logic and structured so another Android app can consume the visual system without inheriting Voyage functionality.

## Design targets

- Light and dark appearance
- System/light/dark appearance modes; optional Android 12+ system dynamic palette and MaterialKolor seed-based palettes
- Dynamic/accent color and palette roles, with animated full ColorScheme transitions on the custom seed-palette path
- Typography and spacing tokens
- Surface, container, frame, outline, and elevation recipes
- Reusable controls: buttons, switches, sliders, rows/preferences, tabs, chips, selectors, dialogs, sheets, and expandable groups
- Selected, unselected, pressed, focused, disabled, and expanded states
- Shape and corner-size variants
- Navigation/surface visual treatments, including floating navigation and glass-like surfaces where supported
- Motion specifications for expansion, selection, and transitions, without importing Voyage navigation behavior

## Source and attribution

The Voyage APK was decompiled for visual inspection. Its observed behavior and appearance are references, not a license to copy unrelated proprietary implementation. AlohaSkin should implement a clean-room, reusable visual system and preserve any applicable third-party notices for assets or dependencies that are actually reused.

## Current status

The extraction is in progress. See [the extraction plan](docs/EXTRACTION_PLAN.md) and [component inventory](docs/COMPONENT_INVENTORY.md). Nothing in this repository should be described as a complete 1:1 extraction until each component has been documented and visually verified in light and dark themes.
