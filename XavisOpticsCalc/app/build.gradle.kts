plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.xavis.opticscalc" // 기존 값 유지해도 됨
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34
    }

    buildFeatures { compose = true }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14" // Compose 1.6.x에 맞춤
    }
}

dependencies {
    // Compose BOM - 2024.06.00 (로그에 나오던 그 버전)
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.9.0")
    // 필요 라이브러리들은 기존 코드 유지
}
