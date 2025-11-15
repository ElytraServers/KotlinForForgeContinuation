plugins {
    alias(libs.plugins.kotlin)
}

dependencies {
    implementation(project(":KFFLang"))
}

tasks.jar {
    manifest.attributes(
        "FMLModType" to "GAMELIBRARY",
        "Automatic-Module-Name" to "thedarkcolour.kotlinforforge.kfflib",
    )
}

publishing {
    publications {
        create("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifactId = "kotlinforforge-lib"
        }
    }
}
