@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")

}

android {
    namespace = "org.nextoptech.rates.core.common"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

}

dependencies {

    implementation(libs.hilt.android)
    testImplementation(libs.junit)
    testImplementation(libs.hilt.android.testing)
    kapt(libs.hilt.compiler)
}