import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    kotlin("jvm") version "1.9.23"

    application
    id("io.papermc.paperweight.userdev") version "1.7.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "ru.luk.reelytras"
version = "0.0.1"

application {
    mainClass.set("$group.ReElytras")
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.codemc.org/repository/maven-public/")
}

dependencies {
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20-R0.1-SNAPSHOT")
}

bukkit {
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    main = application.mainClass.get()
    apiVersion = "1.20"
    authors = listOf("_LuK__")

    commands.create("reelytras") {
        usage = "§cCorrect use: /reelytras [info/reload]"
        permission = "op"
    }
}


tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        enabled = true
    }

    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        enabled = false
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}