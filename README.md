# Material Companion

IntelliJ-family plugin. A Material Design-inspired dark editor color
scheme — cool greys, blues, teals, and purples — with zero forced UI
elements, zero paywalled customization, and zero performance cost.

## Why it exists

Born from real evidence in JetBrains Marketplace reviews of the leading
Material-style theme plugin (18.6M downloads, 80% of recent reviews at 3
stars or fewer despite active development), not assumptions:

- A recent update added a floating action button directly on top of the
  editor that free users cannot disable without paying — "Forcing this
  intrusive UI element on users is a terrible design choice."
- Repeated performance complaints — "my Idea even becomes unresponsive
  at times."
- Basic customization (choosing accent colors, disabling UI chrome)
  locked behind a paid subscription tier.
- This is the same shape of complaint Theme Companion's own "Gap Hunter
  Monokai" scheme was built to fix in a different aesthetic family —
  intrusive forced UI, paywalled basics — just against a Material-style
  competitor instead of a Monokai-style one. It is a distinct niche:
  different audience, different palette family, different competitor,
  confirmed by checking this evidence against Theme Companion's own
  README before starting so as to not duplicate an existing product
  under a new name.

## Why built this way

- **A bundled `EditorColorsScheme`, not a full Theme/Laf.** Same
  reasoning as Theme Companion: the complaints are about editor syntax
  coloring and intrusive UI chrome, not about needing a from-scratch
  IDE theme. A `bundledColorScheme` entry in `plugin.xml` pointing at a
  plain scheme XML file is the smallest, most maintainable way to ship
  this — no custom scheme-loading code, no UI component overrides that
  could themselves become the next forced element.
- **Colors set on the `DEFAULT_*` attribute keys**
  (`DefaultLanguageHighlighterColors`), not per-language keys. Java,
  Kotlin, Python, and JS highlighters all fall back to these defaults,
  so one set of overrides covers all of them without per-language
  duplication.
- **An original palette, not a copy of the competitor's.** Material
  Design's public color tokens (Grey 900 background, Deep Purple,
  Teal, Light Blue, Amber, Pink accents — all from Google's published
  Material color system, not the competitor's proprietary theme
  assets) are combined here into a scheme distinct from both the
  competitor and from this catalog's own "Gap Hunter Monokai" (warm
  pinks/yellows/oranges there; cool blues/teals/purples here).
- **No floating action buttons, no UI chrome overrides at all.** This
  plugin registers exactly one extension point — `bundledColorScheme`
  — and nothing else. There is no code path that could grow into the
  exact complaint this plugin exists to fix.
- **No network access, no telemetry, no license prompts, no forced
  onboarding.** Turn the theme on the same way as any built-in one:
  Settings > Appearance & Behavior > Appearance.

## Usage

Settings > Appearance & Behavior > Appearance > Theme, or Editor > Color
Scheme, and pick "Gap Hunter Material."

## Enterprise / Team Licensing

Need enterprise features, custom color schemes, or team licensing?
Contact us at **gaphunterlabs@gmail.com**.

## Development

```
./gradlew test           # unit tests
./gradlew buildPlugin    # generates build/distributions/*.zip
./gradlew verifyPlugin   # checks compatibility against real IDEs
```

## License

Apache-2.0. See `LICENSE`.
