# Feature-Plan: Purpurne Blume mit 3 Blutenblattern Drop

## Ziel

Eine neue purpurne Blume (`purple_flower`) soll zum Mod hinzugefugt werden. Beim Ernten soll sie `3x purple_petals`
droppen.

## Annahmen

- Projekt nutzt NeoForge mit `DeferredRegister` fur Block- und Item-Registrierung.
- DataGen wird fur LootTables, Models und Tags genutzt.
- Die Blume wird wie eine normale Pflanze (cross-model) umgesetzt.

## Implementierungsplan

### 1) Neues Item registrieren (`purple_petals`)

- Datei: `src/main/java/de/wiederkehr/legomod/item/ModItems.java`
- Neues Item via `ITEMS.registerSimpleItem("purple_petals")` anlegen.
- Zweck: Eindeutiger Drop der purpurnen Blume.

### 2) Neuen Blumen-Block registrieren (`purple_flower`)

- Datei: `src/main/java/de/wiederkehr/legomod/block/ModBlocks.java`
- Neuen Block mit vorhandenem Registry-Pattern (`registerBlock(...)`) registrieren.
- Blocktyp: `BushBlock` (rein dekorativ, ohne Vanilla-Effekte)
- Eigenschaften:
    - `noCollission()`
    - `instabreak()`
    - `sound(SoundType.GRASS)`
  - `offsetType(BlockBehaviour.OffsetType.XZ)` für pflanzentypisches Rendering

### 3) LootTable so anpassen, dass 3 Blutenblatter droppen

- Datei: `src/main/java/de/wiederkehr/legomod/datagen/ModBlockLootTableProvider.java`
- Für `purple_flower` einen Drop-Eintrag erzeugen:
  - **Immer**: `3x ModItems.PURPLE_PETALS` (auch bei Silk Touch/Schere)
  - Die Blume selbst wird nicht gedroppt
- Ergebnis: Beim Ernten (egal mit welchem Werkzeug) werden immer genau 3 Blütenblätter gespawnt.

### 4) DataGen: Blockstates und Models erweitern

- Datei: `src/main/java/de/wiederkehr/legomod/datagen/ModModelProvider.java`
- Fur `purple_flower` ein Pflanzenmodell (`cross`) und Item-Model erzeugen.
- Fur `purple_petals` ggf. einfaches generated item model sicherstellen.
- Texturen vorbereiten:
    - `src/main/resources/assets/legomod/textures/block/purple_flower.png`
    - `src/main/resources/assets/legomod/textures/item/purple_petals.png`

### 5) Tags und Platzierungsregeln

- Datei: `src/main/java/de/wiederkehr/legomod/datagen/ModBlockTagsProvider.java`
- Sicherstellen, dass die Blume in sinnvollen Tags landet (z. B. ersetzbar/werkzeugfrei je nach Projektstil).
- Falls notwendig: Survival-Platzierung auf Gras/Erde via Block-Logik absichern.

### 6) Spracheintraege hinzufugen

- Dateien:
    - `src/main/resources/assets/legomod/lang/en_us.json`
    - `src/main/resources/assets/legomod/lang/de_de.json`
- Neue Keys:
    - `block.legomod.purple_flower`
    - `item.legomod.purple_petals`

### 7) Creative Tab Integration

- Datei: `src/main/java/de/wiederkehr/legomod/creativemodtab/ModCreativeModeTabs.java`
- `purple_flower` und `purple_petals` im passenden Tab anzeigen.

### 8) World Generation implementieren

- Dateien:
  - `src/main/java/de/wiederkehr/legomod/worldgen/feature/...`
  - `src/main/resources/data/legomod/worldgen/configured_feature/...`
  - `src/main/resources/data/legomod/worldgen/placed_feature/...`
- Neue Configured- und PlacedFeature für `purple_flower` erzeugen.
- Platzierung: Im Biome-Tags oder spezifischen Biomes definieren.
- Spawn-Bedingungen: z. B. auf Gras/Erde in gemäßigten Biomen.

### 9) DataGen und Build validieren

- DataGen ausführen und generierte Ressourcen prüfen.
- Kompilierung + Client-Start testen:
    - Block platzierbar
  - Richtige Darstellung
  - Beim Ernten exakt 3 Blütenblätter
  - Blume spawnt natürlich in der Welt

## Test-Checkliste

- [ ] `purple_flower` ist im Creative Tab sichtbar.
- [ ] Blockstate/Model/Texture werden korrekt geladen.
- [ ] Beim Abbau droppen genau `3x purple_petals` (auch mit Schere/Silk Touch).
- [ ] Die Blume wird nicht selbst gedroppt.
- [ ] `purple_flower` spawnt natürlich in der Welt (WorldGen funktioniert).
- [ ] Lokalisierung in EN/DE funktioniert.

## Klärung der offenen Fragen

1. ✅ **Bei Schere/Silk Touch:** Weiterhin `3x purple_petals` droppen (nicht die Blume selbst)
2. ✅ **Blocktyp:** Rein dekorativ mit `BushBlock` (ohne Vanilla-Effekte)
3. ✅ **World Generation:** Blume soll natürlich in der Welt spawnen (WorldGen implementieren)

