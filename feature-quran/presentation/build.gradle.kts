
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
    coreLibraryDesugaring(Desugar.desugarJDKLibs)
}
