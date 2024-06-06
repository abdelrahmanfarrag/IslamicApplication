plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/base-module.gradle")
android {
    namespace = "com.islamic.home_domain"
}
dependencies {
    implementation(project(Module.CORE_DOMAIN))
    implementation (Google.LOCATION_SERVICES)
    testImplementation(project(Module.CORE_DOMAIN))
}
