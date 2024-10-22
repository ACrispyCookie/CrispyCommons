# CrispyCommons

CrispyCommons is a powerful and modular library designed for Minecraft plugin developers using the Spigot API. This library provides a robust set of tools and abstractions to simplify and enhance the development of visual elements such as Action bars, Boss bars, Holograms, Scoreboards, Tab lists, Titles, Particles, Name tags and GUIs such as books and inventories.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Examples](#examples)
- [Documentation](#documentation)
- [Contributing](#contributing)

## Features
- **Elements**: A powerful abstraction for managing dynamic or animated content, which can change based on different contexts and dynamically update.
- **Visuals**:
    - **Time-to-Live Management**: Control the lifespan of visual elements with flexible TTL settings.
    - **Action bars**: Easily create, manage, and display action bars to players with dynamic text and time-to-live (TTL) control.
    - **Boss bars**: Customizable boss bars with dynamic colors, overlays, and progress.
    - **Holograms**: Display floating text holograms with dynamic content.
    - **Scoreboards**: Simplify the creation of dynamic scoreboards with support for titles and lines.
    - **Tab lists**: Customize the header and footer of the player tab list with dynamic text elements.
    - **Titles**: Manage titles and subtitles with fade-in and fade-out effects.
    - **Particles**: Display particles with various effects, including colored and rendered particles.
    - **Name tags**: Customizable name tags with support for prefixes, suffixes, and additional display lines.
- **GUIs**:
  - **Books**: Create interactive books that can be used to display multiple pages of text and handle click events.
  - **Menus**: Design and manage paginated menus with dynamic sections, customizable layouts, and item click handling.
- **Extra helpful abstractions**: More abstractions such as CrispyItemStack that make it easier to deal with Spigot API classes.

## Installation

To include CrispyCommons in your Minecraft plugin project:

- **Add CrispyCommons to your project dependencies**:
    - For Maven:
      ```xml
      <dependency>
          <groupId>dev.acrispycookie</groupId>
          <artifactId>CrispyCommons</artifactId>
          <version>1.0-SNAPSHOT</version>
          <scope>provided</scope>
      </dependency>
      ```
    - For Gradle:
      ```groovy
      dependencies {
          compileOnly 'dev.acrispycookie:CrispyCommons:1.0.0'
      }
      ```
## Examples

CrispyCommons is built to be as intuitive as possible, with a consistent API for all visual elements. Here's some simple examples to show how easily you can create more complex visuals.

#### Displaying an action bar to 2 specific players with a static text:
```java
CrispyActionbar actionbar = CrispyActionbar.builder()
    .setText(TextElement.simple("Welcome to the server!"))
    .addPlayer(Bukkit.getPlayerExact("ACrispyCookie"))
    .addPlayer(Bukkit.getPlayerExact("notch"))
    .setPublic(true)
    .build();

actionbar.show();
```

#### Displaying a scoreboard to every online player with an animated title and a dynamic line that displays the current player count:
```java
CrispyScoreboard scoreboard = CrispyScoreboard.builder()
    .setTitle(TextElement.animated(frames)) // frames is a collections of strings that the title will cycle through
    .addTextLine(TextElement.dynamic(() -> Bukkit.getOnlinePlayers().size(), 0, 20)) // Updates every 20 ticks = 1 second
    .setPublic(true) // Every online player can see it
    .build();

scoreboard.show();
```

#### Creating a boss bar that is half-full, blue and displays the health of each player as the text. The boss bar has TTL of 10 seconds for each player:
```java
CrispyBossBar bossbar = CrispyBossBar.builder()
    .setText(TextElement.dynamicPersonal((p) -> "&e" + p.getPlayer().getHealth(), 0, 1)) // Each player sees their own health.
    .setProgress(GeneralElement.simple(0.5F))
    .setColor(GeneralElement.simple(BossBar.Color.BLUE))
    .setOverlay(GeneralElement.simple(BossBar.Overlay.PROGRESS))
    .setTimeToLive(TimeToLiveElement.simple(10L, TimeToLiveElement.StartMode.PER_PLAYER))
    .build();

bossbar.show(); // Currently no viewers are added to the visual so the elements won't yet start updating.

bossbar.addPlayer(Bukkit.getPlayerExact("ACrispyCookie")); // Here a player is added to the visual so the dynamic elements start updating.
```

#### Creating a simple server selector menu
```java
// Define the display of the skyblock server icon
ItemElement<Void> skyblockDisplay = ItemElement.dynamic(() ->
    new CrispyItemStack(Material.GRASS)
    .name("&aSkyblock Server")
    .lore("&d" + sbOnlinePlayers + " online!" +
            "\n&eClick to join!"),
    0, 1);

// Define the display of the survival server icon
ItemElement<Void> survivalDisplay = ItemElement.dynamic(() ->
    new CrispyItemStack(Material.GRASS)
    .name("&3Survival Server")
    .lore("&d" + survivalOnlinePlayer + " online!" +
            "\n&eClick to join!"),
    0, 1);

// Define the menu item for the skyblock item which defines the click action
MenuItem skyblockButton = MenuItem.loadedItem(skyblockDisplay, (menu, player) -> sendPlayerToSkyblock());

// Define the menu item for the survival item which defines the click action
MenuItem survivalButton = MenuItem.loadedItem(skyblockDisplay, (menu, player) -> sendPlayerToSurvival());

MenuPage serverSelector = MenuPage.createEmpty("Server selector", 3, 9, (player, itemStack) -> false);
// Everything works with section which is a cool way to reuse parts of a menu to another menu
serverSelector.addStaticSection(new Point(1, 2), 0, StaticSection.oneItem(skyblockButton));
serverSelector.addStaticSection(new Point(1, 2), 0, StaticSection.oneItem(survivalButton));

CrispyMenu menu = CrispyMenu.builder()
    .addPage(serverSelector)
    .build();

menu.open(Bukkit.getPlayerExact("ACrispyCookie"));
```

## Documentation
You can view the Javadoc for this project [here](https://acrispycookie.dev/projects/crispycommons/javadoc)

More documentation and tutorials coming soon!

## Contributing
You're welcome to contribute to the project. Whether you want to submit a bug report, request a feature, or contribute code, here’s how you can get involved:

1. Fork the repository on GitHub.
2. Clone your fork: git clone https://github.com/ACrispyCookie/CrispyCommons.git
3. Create a new branch: git checkout -b my-feature-branch
4. Make your changes: Implement your feature or fix.
5. Commit your changes: git commit -am 'Add new feature'
6. Push to your branch: git push origin my-feature-branch
7. Create a pull request on GitHub.
