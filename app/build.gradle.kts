plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.androidx.navigation.safeargs)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.serialization)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.example.recyclerviewswipetestapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.recyclerviewswipetestapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.runtime.ktx)
    // NavHostFragmentのために
    implementation(libs.androidx.navigation.fragment.ktx)
    // Toolbarのために
    implementation(libs.androidx.navigation.ui.ktx)
    // Dagger-Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    kapt(libs.dagger.hilt.android.compiler)

    // メモリリーク検出ライブラリ
    debugImplementation(libs.leakcanary)
}
kapt {
    // エラータイプの修正を有効化
    correctErrorTypes = true
}