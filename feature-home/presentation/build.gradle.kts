plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/compose-module.gradle")
android {
    namespace = "com.islamic.home_presentation"
}
dependencies {
    implementation(project(Module.home_domain))
}
