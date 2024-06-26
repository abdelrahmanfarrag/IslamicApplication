
plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/compose-module.gradle")
android {
    namespace = "com.feature_quran.presentation"
    defaultConfig {
        multiDexEnabled = true
    }
}
dependencies {
    implementation(project(Module.FEATURE_QURAN_DOMAIN))
    implementation(project(Module.CORE_DOMAIN))
    implementation(project(Module.CORE_PRESENTATION))
    implementation("androidx.compose.material3:material3-android:1.2.1")
    coreLibraryDesugaring(Desugar.desugarJDKLibs)
}
