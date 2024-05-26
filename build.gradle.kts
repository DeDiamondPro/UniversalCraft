import gg.essential.gradle.util.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("gg.essential.multi-version")
    id("gg.essential.defaults")
    id("gg.essential.defaults.maven-publish")
}

group = "gg.essential"

java.withSourcesJar()
tasks.compileKotlin.setJvmDefault(if (platform.mcVersion >= 11400) "all" else "all-compatibility")
loom.noServerRunConfigs()

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21")
}

repositories {
    maven("https://maven.neoforged.net/releases/")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        languageVersion = "1.6"
        apiVersion = "1.6"
    }
}

tasks.jar {
    manifest {
        attributes(mapOf("FMLModType" to "LIBRARY"))
    }
}

version = "337+diamond.neoforge"

publishing {
    repositories {

        maven {
            name = "diamond"

            url = uri("https://maven.dediamondpro.dev/releases")

            credentials {
                username = System.getenv("MAVEN_DIAMOND_USER")
                password = System.getenv("MAVEN_DIAMOND_PASSWORD")
            }

            version = project.version
        }
    }
}