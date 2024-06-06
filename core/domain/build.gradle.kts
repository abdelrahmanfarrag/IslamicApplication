plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/base-module.gradle")
android {
    namespace = "com.islamic.core_domain"
}
dependencies{
    implementation (Google.LOCATION_SERVICES)

}