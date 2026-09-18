plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}
// kotlin-js-store/wasm/yarn.lock is gitignored, so let Gradle regenerate it
// whenever dependencies change instead of failing the build on a mismatch.
plugins.withType<org.jetbrains.kotlin.gradle.targets.wasm.yarn.WasmYarnPlugin> {
    the<org.jetbrains.kotlin.gradle.targets.wasm.yarn.WasmYarnRootEnvSpec>().apply {
        yarnLockMismatchReport = org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport.NONE
        reportNewYarnLock = false
        yarnLockAutoReplace = true
    }
}
