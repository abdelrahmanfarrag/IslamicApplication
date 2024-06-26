
plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/base-module.gradle")
android {
    namespace = "com.islamic.surrah_domain"
}
dependencies {
    implementation(project(Module.CORE_DOMAIN))
}

