# CrispyCommons

CrispyCommons is a powerful and modular library designed for Minecraft plugin developers using the Spigot API. This library provides a robust set of tools and abstractions to simplify and enhance the development of visual elements such as Action Bars, Boss Bars, Holograms, Scoreboards, Tab Lists, Titles, and more. With CrispyCommons, developers can easily create and manage complex visual components with minimal effort, allowing for a more streamlined development process and richer user experience.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
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
## Usage

CrispyCommons is built to be as intuitive as possible, with a consistent API for all visual elements. Here's an example of how to create and display a simple action bar:

#### Displaying an action bar to 2 specific players with a static text.
```java
CrispyActionbar actionbar = CrispyActionbar.builder()
    .setText(TextElement.simple("Welcome to the server!"))
    .setPublic(true)
    .build();

actionbar.show();
```

## Documentation


## Contributing
You're welcome to contribute to the project. Whether you want to submit a bug report, request a feature, or contribute code, here’s how you can get involved:

1. Fork the repository on GitHub.
2. Clone your fork: git clone https://github.com/ACrispyCookie/CrispyCommons.git
3. Create a new branch: git checkout -b my-feature-branch
4. Make your changes: Implement your feature or fix.
5. Commit your changes: git commit -am 'Add new feature'
6. Push to your branch: git push origin my-feature-branch
7. Create a pull request on GitHub.
