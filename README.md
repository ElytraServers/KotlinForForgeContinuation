# Kotlin For Forge Continuation

## Why a Fork?

[KotlinForForge#116](https://github.com/thedarkcolour/KotlinForForge/issues/116)

It's because the upstream KFF 4.x is, in fact, stopped updating, and I can't figure out how they build their JARs, so I
fully rewrite the buildscript.

The root project is the mod itself. The standard library and the KFFLib is in their separated folders, combined by
Jar-in-Jar.

## What's New?

- Updated the embedded dependencies including Kotlin from v1.9 to v2.2.

## Usage

[![](https://jitpack.io/v/ElytraServers/KotlinForForgeContinuation.svg)](https://jitpack.io/#ElytraServers/KotlinForForgeContinuation)

```kotlin
plugins {
	// make sure to use a same version.
	kotlin("jvm") version "2.2.21"
}

repositories {
	maven {
		url = uri("https://jitpack.io")
	}
	// ...
}

dependencies {
	// import the language provider
	// so that you can use Kotlin for developing and constructing your mod
	// make sure that `modLoader="kotlinforforge"` in "mods.toml".
	implementation("com.github.ElytraServers.KotlinForForgeContinuation:kotlinforforge-language-provider:<VERSION>")

	// import the KFFLib,
	// which has multiple extension functions.
	implementation("com.github.ElytraServers.KotlinForForgeContinuation:kotlinforforge-lib:<VERSION>")

	// import the mod itself.
	// I suppose you don't need this!
	implementation("com.github.ElytraServers.KotlinForForgeContinuation:kotlinforforge:<VERSION>")
}
```

The very important thing is that not to break the existing stuffs, so if you find anything behaves differently with the
upstream version, please make an issue.
