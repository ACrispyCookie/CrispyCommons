plugins {
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19"
}

dependencies {
    // Core abstraction
    api(project(":crispycommons-core-spigot"))

    // NMS Dependency
    paperweight.paperDevBundle("1.21.5-R0.1-SNAPSHOT")
}