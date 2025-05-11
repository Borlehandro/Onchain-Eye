package io.borlehandro.onchaineye.buildlogic.plugin

import com.android.build.api.dsl.androidLibrary
import io.borlehandro.onchaineye.buildlogic.utils.BuildConst
import io.borlehandro.onchaineye.buildlogic.utils.androidKotlinMultiplatformPlugin
import io.borlehandro.onchaineye.buildlogic.utils.compileSdk
import io.borlehandro.onchaineye.buildlogic.utils.kotlinMultiplatformPlugin
import io.borlehandro.onchaineye.buildlogic.utils.kotlinStdlib
import io.borlehandro.onchaineye.buildlogic.utils.kotlinTest
import io.borlehandro.onchaineye.buildlogic.utils.minSdk
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

@Suppress("Unused")
class MultiplatformLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val versionCatalogs = project.extensions.getByType<VersionCatalogsExtension>()
        val libs = versionCatalogs.named("libs")

        project.plugins.apply(libs.kotlinMultiplatformPlugin)
        project.plugins.apply(libs.androidKotlinMultiplatformPlugin)

        val kotlinExtension = project.extensions.getByType<KotlinMultiplatformExtension>()

        kotlinExtension.apply {
            androidLibrary {
                compileSdk = libs.compileSdk
                minSdk = libs.minSdk
            }

            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64()
            ).forEach { iosTarget ->
                iosTarget.binaries.framework {
                    baseName = BuildConst.APP_NAME
                    isStatic = true
                }
            }

            @OptIn(ExperimentalWasmDsl::class)
            wasmJs {
                moduleName = BuildConst.MODULE_NAME
                browser {
                    val rootDirPath = project.rootDir.path
                    val projectDirPath = project.projectDir.path
                    commonWebpackConfig {
                        outputFileName = "${BuildConst.MODULE_NAME}.js"
                        devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                            static = (static ?: mutableListOf()).apply {
                                // Serve sources to debug inside browser
                                add(rootDirPath)
                                add(projectDirPath)
                            }
                        }
                    }
                }
                binaries.executable()
            }

            sourceSets.apply {
                commonMain {
                    dependencies {
                        implementation(libs.kotlinStdlib)
                    }
                }

                commonTest {
                    dependencies {
                        implementation(libs.kotlinTest)
                    }
                }
            }
        }
    }
}