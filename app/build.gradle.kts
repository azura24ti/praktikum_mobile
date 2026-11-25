plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.azura_tic"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.azura_tic"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.gridlayout:gridlayout:1.0.0") // Tahap 4: GridLayout
    implementation("androidx.viewpager2:viewpager2:1.1.0") // Tahap 5: ViewPager2
    implementation("com.tbuonomo:dotsindicator:5.1.0") // Tahap 6: DotsIndicator
    implementation("androidx.recyclerview:recyclerview:1.3.2") // Tahap 3 dan 4: RecyclerView
    implementation("com.github.bumptech.glide:glide:4.16.0") // Tahap 4: memuat gambar dari URL
    val roomVersion = "2.8.4"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
}