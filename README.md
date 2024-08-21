# CrispyCommons

CrispyCommons is a powerful and modular library designed for Minecraft plugin developers using the Spigot API. This library provides a robust set of tools and abstractions to simplify and enhance the development of visual elements such as Action Bars, Boss Bars, Holograms, Scoreboards, Tab Lists, Titles, and more. With CrispyCommons, developers can easily create and manage complex visual components with minimal effort, allowing for a more streamlined development process and richer user experience.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Documentation](#documentation)
- [Contributing](#contributing)

## Features

- **Action Bars**: Easily create, manage, and display action bars to players with dynamic text and time-to-live (TTL) control.
- **Boss Bars**: Customizable boss bars with dynamic colors, overlays, and progress.
- **Holograms**: Display floating text holograms with dynamic content.
- **Scoreboards**: Simplify the creation of dynamic scoreboards with support for titles and lines.
- **Tab Lists**: Customize the header and footer of the player tab list with dynamic text elements.
- **Titles**: Manage titles and subtitles with fade-in and fade-out effects.
- **Particles**: Display particles with various effects, including colored and rendered particles.
- **Name Tags**: Customizable name tags with support for prefixes, suffixes, and additional display lines.
- **Visual Abstraction**: Unified interfaces and abstract classes for consistent and easy-to-use visual element management.
- **Time-to-Live Management**: Control the lifespan of visual elements with flexible TTL settings.
- **Player Context Management**: Automatically handles player-specific data contexts for dynamic content updates.

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

```java
import dev.acrispycookie.crispycommons.api.visual.actionbar.CrispyActionbar;
import dev.acrispycookie.crispycommons.api.elements.text.TextElement;

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
