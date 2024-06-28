
plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/compose-module.gradle")
android {
    namespace = "com.islamic.surrah_presentation"
    defaultConfig {
        multiDexEnabled = true
    }
}
dependencies {
    implementation(project(Module.FEATURE_SURRAH_DOMAIN))
    implementation(project(Module.CORE_DOMAIN))
    implementation(project(Module.CORE_PRESENTATION))
    coreLibraryDesugaring(Desugar.desugarJDKLibs)
    implementation(ExoPlayer.EXO_PLAYER_UI)
 //   implementation(ExoPlayer.EXO_PLAYER_COMMON)
    implementation(ExoPlayer.EXO_PLAYER_MEDIA)
}
