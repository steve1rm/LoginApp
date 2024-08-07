plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.junit5)
}

android {
    namespace = "me.androidbox.authentication.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    }
    packaging {
        this.resources.excludes.add("META-INF/LICENSE.md")
        this.resources.excludes.add("META-INF/LICENSE-notices.md")
    }

}

dependencies {
    implementation(project(":authentication:domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.koin.core)
    implementation(libs.kermit)
    implementation(libs.firebase.auth)

    testImplementation(libs.junit5.api)
    testImplementation(libs.junit5.params)
    runtimeOnly(libs.junit5.engine)
    testImplementation(libs.assertk)
    testImplementation(libs.turbine)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockito.kotlin)
}