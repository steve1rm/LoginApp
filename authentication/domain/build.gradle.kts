plugins {
    alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.koin.core)

    testImplementation(libs.junit5.api)
    testImplementation(libs.junit5.params)
    runtimeOnly(libs.junit5.engine)
    testImplementation(libs.assertk)
    testImplementation(libs.turbine)
}