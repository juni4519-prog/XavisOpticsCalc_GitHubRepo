plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.xavis.opticscalc"   // 기존과 동일하게 유지
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34
    }

    // ✅ Compose 사용 선언
    buildFeatures { compose = true }

    // ✅ Compose 컴파일러 버전 고정
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    // ✅ Compose BOM (로그에 쓰이던 2024.06.00)
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.9.0")

    // ✅ XML 테마(Theme.Material3.*)를 쓰기 위해 필요한 Material Components (뷰) 라이브러리
    implementation("com.google.android.material:material:1.12.0")

    // (여기에 기존에 있던 다른 의존성들—예: 코틀린, 코루틴, 테스트 등—그대로 유지)
}
