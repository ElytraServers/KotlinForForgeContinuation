plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.shadow)
}

val include: Configuration by configurations.creating

dependencies {
    fun dependency(d: Any) {
        include(d)
        implementation(d)
    }

    dependency(libs.kotlin.stdlib)
    dependency(libs.kotlin.stdlib.jdk7)
    dependency(libs.kotlin.stdlib.jdk8)
    dependency(libs.kotlin.reflect)
    dependency(libs.kotlinx.coroutines.core)
    dependency(libs.kotlinx.coroutines.core.jvm)
    dependency(libs.kotlinx.coroutines.jdk8)
    dependency(libs.kotlinx.serialization.core)
    dependency(libs.kotlinx.serialization.json)
}

tasks.jar {
    manifest.attributes(
        "FMLModType" to "LANGPROVIDER",
        "Automatic-Module-Name" to "thedarkcolour.kotlinforforge.kfflang",
    )
}

tasks.shadowJar {
    configurations = setOf(include)
}

publishing {
    publications {
        create("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifactId = "kotlinforforge-language-provider"
        }
    }
}
