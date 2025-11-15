# Kotlin For Forge Continuation

[KotlinForForge#116](https://github.com/thedarkcolour/KotlinForForge/issues/116)

Because KFF 4.x is in fact stopped updating, and I can't figure out how upstream build their JARs, so I fully rewrite
the buildscript.

The root project is the mod itself. The standard library and the KFFLib is in their separated folders, combined by
Jar-in-Jar.

## What's New?

- Updated the embedded dependencies including Kotlin from v1.9 to v2.2.
