plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.androidx.navigation.safeargs) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
}
allprojects {
    afterEvaluate {
        tasks.withType<JavaCompile>().configureEach {
            options.compilerArgs.addAll(listOf("-Xlint:unchecked", "-Xlint:deprecation"))
        }
    }
}