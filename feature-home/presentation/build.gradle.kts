plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/compose-module.gradle")
android {
    namespace = "com.islamic.home_presentation"
    defaultConfig {
        multiDexEnabled = true
    }
}
dependencies {
    implementation(project(Module.home_domain))
    coreLibraryDesugaring(Desugar.desugarJDKLibs)
}
