plugins {
    `kotlin-dsl`
}

group = "io.borlehandro.onchaineye.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.kotlinMultiplatformLibrary)
    compileOnly(libs.compose.multiplatform)
    compileOnly(libs.compose.compiler)
}

gradlePlugin {
    plugins {
        register("multiplatformLibrary") {
            id = "io.borlehandro.onchaineye.library"
            implementationClass = "io.borlehandro.onchaineye.buildlogic.plugin.MultiplatformLibraryPlugin"
        }
        register("composeLibrary") {
            id = "io.borlehandro.onchaineye.compose"
            implementationClass = "io.borlehandro.onchaineye.buildlogic.plugin.MultiplatformComposePlugin"
        }
    }
}