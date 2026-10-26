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
