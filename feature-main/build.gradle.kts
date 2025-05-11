plugins {
    id("io.borlehandro.onchaineye.library")
    id("io.borlehandro.onchaineye.compose")
}

kotlin {

    androidLibrary {
        namespace = "io.borlehandro.onchaineye.feature.main"
    }
}