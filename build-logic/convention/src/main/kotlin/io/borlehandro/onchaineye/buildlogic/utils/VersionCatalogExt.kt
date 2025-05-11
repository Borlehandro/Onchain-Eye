package io.borlehandro.onchaineye.buildlogic.utils

import org.gradle.api.artifacts.VersionCatalog

// Versions
internal val VersionCatalog.minSdk get() = findVersion("android-minSdk").get().requiredVersion.toInt()
internal val VersionCatalog.compileSdk get() = findVersion("android-compileSdk").get().requiredVersion.toInt()

// Libs
internal val VersionCatalog.kotlinStdlib get() = findLibrary("kotlin-stdlib").get()
internal val VersionCatalog.kotlinTest get() = findLibrary("kotlin-test").get()
internal val VersionCatalog.composeRuntime get() = findLibrary("androidx-lifecycle-runtime-compose").get()
internal val VersionCatalog.viewModel get() = findLibrary("androidx-lifecycle-viewmodel").get()
internal val VersionCatalog.androidActivityCompose get() = findLibrary("androidx-activity-compose").get()

// Plugins
internal val VersionCatalog.kotlinMultiplatformPlugin get() = findPlugin("kotlinMultiplatform").get().get().pluginId
internal val VersionCatalog.androidKotlinMultiplatformPlugin get() = findPlugin("androidKotlinMultiplatformLibrary").get().get().pluginId
internal val VersionCatalog.composeMultiplatformPlugin get() = findPlugin("composeMultiplatform").get().get().pluginId
internal val VersionCatalog.composeCompilerPlugin get() = findPlugin("composeCompiler").get().get().pluginId