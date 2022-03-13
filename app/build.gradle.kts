plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.apollographql.apollo3").version("3.1.0")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}


android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.nestor.spacex"
        minSdk = 28
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            buildConfigField(
                type = "String",
                name = "SERVER_URL",
                value = "\"https://apollo-fullstack-tutorial.herokuapp.com/graphql\""
            )
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dataBinding {
        isEnabled = true
    }
    viewBinding.isEnabled = true
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //  Apollo 3.1.0
    implementation("com.apollographql.apollo3:apollo-runtime:3.1.0")

    // hilt
    implementation("com.google.dagger:hilt-android:2.41")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt("com.google.dagger:hilt-compiler:2.41")

    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.1")

    //  paging3
    implementation("androidx.paging:paging-runtime:3.1.1")

    //  coil
    implementation("io.coil-kt:coil:2.0.0-rc01")

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

apollo {
    packageName.set("com.nestor.spacex")
}