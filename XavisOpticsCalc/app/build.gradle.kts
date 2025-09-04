plugins {
    id("com.android.application")
    kotlin("android")
}

kotlin {
    jvmToolchain(17)
}

android {
    // --- Java/Kotlin 설정 ---
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }

    // --- Compose 설정 (단 한 번만) ---
    buildFeatures { compose = true }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15" // 하나로 통일
    }

    // --- 빌드타입 ---
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // --- 패키징 ---
    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

dependencies {
    // Compose BOM: 버전 정합성 고정
    implementation(platform("androidx.compose:compose-bom:2024.09.01"))

    // 핵심 Compose 모듈
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-text")        // ★ KeyboardOptions가 여기
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")

    // 프리뷰/툴링
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
}
