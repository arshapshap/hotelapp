@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.arshapshap.hotelapp.core.network"
    compileSdk = libs.versions.compileSdk.get().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        debug {
            buildConfigField("String", "BASE_API_URL", "\"https://run.mocky.io/v3/\"")
        }
        release {
            buildConfigField("String", "BASE_API_URL", "\"https://run.mocky.io/v3/\"")
        }
    }
}

dependencies {

    implementation(libs.koin)
    implementation(libs.okhttp)

    api(libs.retrofit.core)
    api(libs.retrofit.converter.gson)
}