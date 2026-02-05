dependencies {
    // Sibling Dependency
    api(project(":crispycommons-core"))

    // External Dependencies
    compileOnly("org.spigotmc:spigot-api:1.21.1-R0.1-SNAPSHOT")
    compileOnly("com.mojang:authlib:6.0.54")

    api("net.kyori:adventure-platform-bukkit:4.3.4")
    api("com.github.cryptomorin:XSeries:11.2.1")
    api("dev.dejvokep:boosted-yaml:1.3.7")
}