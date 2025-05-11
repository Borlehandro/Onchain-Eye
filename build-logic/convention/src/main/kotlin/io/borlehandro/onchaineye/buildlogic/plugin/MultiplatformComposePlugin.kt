package io.borlehandro.onchaineye.buildlogic.plugin

import io.borlehandro.onchaineye.buildlogic.utils.androidActivityCompose
import io.borlehandro.onchaineye.buildlogic.utils.composeCompilerPlugin
import io.borlehandro.onchaineye.buildlogic.utils.composeMultiplatformPlugin
import io.borlehandro.onchaineye.buildlogic.utils.composeRuntime
import io.borlehandro.onchaineye.buildlogic.utils.viewModel
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformComposePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val versionCatalogs = project.extensions.getByType<VersionCatalogsExtension>()
        val libs = versionCatalogs.named("libs")

        project.plugins.apply(libs.composeMultiplatformPlugin)
        project.plugins.apply(libs.composeCompilerPlugin)

        val kotlinExtension = project.extensions.getByType<KotlinMultiplatformExtension>()
        val composeExtension = project.extensions.getByType<ComposeExtension>()

        kotlinExtension.apply {

            sourceSets.apply {
                androidMain.dependencies {
                    implementation(composeExtension.dependencies.preview)
                    implementation(libs.androidActivityCompose)
                }
                commonMain.dependencies {
                    implementation(composeExtension.dependencies.runtime)
                    implementation(composeExtension.dependencies.foundation)
                    implementation(composeExtension.dependencies.material)
                    implementation(composeExtension.dependencies.ui)
                    implementation(composeExtension.dependencies.components.resources)
                    implementation(composeExtension.dependencies.components.uiToolingPreview)
                    implementation(libs.viewModel)
                    implementation(libs.composeRuntime)
                }
            }
        }
    }
}