plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.moddevgradle)

    `maven-publish`
}

group = "cn.taskeren"
version = "1.0-SNAPSHOT"

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
        create("client") {
            client()
            gameDirectory = file("run/client")
        }
    }

    mods {
        create("kotlinforforge") {
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
