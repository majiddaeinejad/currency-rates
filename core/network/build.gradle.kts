@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-kapt")

}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        buildConfig = true
    }
    namespace = "org.nextoptech.core.network"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {

    implementation(libs.hilt.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.retrofit.kotlin.datetime)
    implementation(project(":core:common"))
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.junit)
    kapt(libs.hilt.compiler)

}