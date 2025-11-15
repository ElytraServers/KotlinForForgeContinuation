plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.moddevgradle)
    alias(libs.plugins.gitVersion)

    `maven-publish`
}

group = "cn.taskeren"

val gitVersion: groovy.lang.Closure<String> by extra
try {
    version = gitVersion()
} catch (e: Exception) {
    println("Failed to read version from git")
    e.printStackTrace()
}

repositories {
    mavenCentral()
}

dependencies {
}

kotlin {
    jvmToolchain(17)
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "net.neoforged.moddev.legacyforge")
    apply(plugin = "maven-publish")

    legacyForge {
        version = "${project.property("mc_version")}-${project.property("forge_version")}"
    }

    kotlin {
        explicitApi()
    }
}

dependencies {
    // use the shadowJar output of KFFLang
    implementation(files(tasks.getByPath(":KFFLang:shadowJar")))
    implementation(project(":KFFLib"))

    jarJar(files(tasks.getByPath(":KFFLang:shadowJar")))
    jarJar(project(":KFFLib"))
}

legacyForge {
    runs {
        val client by creating {
            client()
            gameDirectory = file("run/client")
        }
    }

    mods {
        val kotlinforforge by creating {
            sourceSet(sourceSets.main.get())
        }
    }
}

publishing {
    publications {
        create("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifactId = "kotlinforforge"
        }
    }
}
