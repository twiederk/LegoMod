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
- Blocktyp: `FlowerBlock` oder `BushBlock` (abhängig vom gewunschten Effekt).
- Eigenschaften:
    - `noCollission()`
    - `instabreak()`
    - `sound(SoundType.GRASS)`
    - `offsetType(BlockBehaviour.OffsetType.XZ)` fur pflanzentypisches Rendering

### 3) LootTable so anpassen, dass 3 Blutenblatter droppen

- Datei: `src/main/java/de/wiederkehr/legomod/datagen/ModBlockLootTableProvider.java`
- Fur `purple_flower` einen Drop-Eintrag erzeugen:
    - Standard: immer `3x ModItems.PURPLE_PETALS`
    - Optional (zu klaren): Verhalten bei Silk Touch / Schere
- Ergebnis: Beim Ernten der Blume werden 3 Blutenblatter gespawnt.

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

### 8) DataGen und Build validieren

- DataGen ausfuhren und generierte Ressourcen prufen.
- Kompilierung + Client-Start testen:
    - Block platzierbar
    - richtige Darstellung
    - beim Ernten exakt 3 Blutenblatter

## Test-Checkliste

- [ ] `purple_flower` ist im Creative Tab sichtbar.
- [ ] Blockstate/Model/Texture werden korrekt geladen.
- [ ] Beim normalen Abbau droppen genau `3x purple_petals`.
- [ ] Verhalten mit Silk Touch/Schere ist wie gewunscht.
- [ ] Lokalisierung in EN/DE funktioniert.

## Offene Fragen

1. Soll bei Silk Touch/Schere die Blume selbst droppen oder weiterhin `3x purple_petals`?
2. Soll die Blume einen Vanilla-Effekt haben (wie `FlowerBlock` mit MobEffect), oder rein dekorativ sein (`BushBlock`)?
3. Soll die Blume naturlich in der Welt spawnen (WorldGen), oder nur per Creative/Commands verfugbar sein?

