plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version ("1.9.23")
}

android {
    namespace = "com.islamic.app"
    compileSdk = 34

    bundle {
        language {
            enableSplit = false
        }
    }
    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            enableUnitTestCoverage = true
        }
        release {
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
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {

    implementation(Compose.compiler)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.hiltNavigationCompose)
    implementation(Compose.material)
    implementation(Compose.runtime)
    implementation(Compose.navigation)
    implementation(Compose.viewModelCompose)
    implementation(Compose.activityCompose)
    implementation(Compose.COMPOSE_RUNTIME)

    implementation(Hilt.hiltAndroid) {
        exclude(group = "com.squareup", module = "javapoet")
    }
    implementation("com.squareup:javapoet:1.13.0")
    implementation(project(":local"))
    kapt(Hilt.hiltCompiler)
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    implementation(project(Module.api))
    implementation(project(Module.home_data))
    implementation(project(Module.home_domain))
    implementation(project(Module.home_presentation))
    implementation(project(Module.CORE_DATA))
    implementation(project(Module.CORE_DOMAIN))
    implementation(project(Module.CORE_PRESENTATION))
    implementation(project(Module.SERVICES))
    implementation(project(Module.FEATURE_QURAN_DATA))
    implementation(project(Module.FEATURE_QURAN_DOMAIN))
    implementation(project(Module.FEATURE_QURAN_PRESENTATION))
    implementation(project(Module.FEATURE_SURRAH_DATA))
    implementation(project(Module.FEATURE_SURRAH_DOMAIN))
    implementation(project(Module.FEATURE_SURRAH_PRESENTATION))

    implementation(Compose.KOTLINX_SERIALIZATION)


    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)


    implementation(Google.material)

    implementation(WorkManager.WORK_MANAGER)
    implementation(WorkManager.HILT_WORKER)
    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)

    kapt(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)

    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.composeUiTest)
    testImplementation(Testing.mockWebServer)

    androidTestImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.composeUiTest)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.hiltTesting)
    kaptAndroidTest(Hilt.hiltCompiler)
    androidTestImplementation(Testing.testRunner)
    testImplementation(Testing.MOCKITO_INLINE)
    testImplementation(Testing.MOCKITO_KOTLIN)
    coreLibraryDesugaring(Desugar.desugarJDKLibs)
}
kapt {
    correctErrorTypes = true
}