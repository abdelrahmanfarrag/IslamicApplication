plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.islamic.core_presentation"
}
dependencies {
    implementation(project(Module.CORE_DOMAIN))
}
