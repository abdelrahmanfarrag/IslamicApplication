plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/base-module.gradle")
android {
    namespace = "com.islamic.core_data"
}
dependencies {
    implementation(project(Module.api))
    implementation(project(Module.CORE_DOMAIN))

}