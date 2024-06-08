plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/base-module.gradle")
android {
    namespace = "com.islamic.home_data"
}
dependencies{
    implementation(project(Module.home_domain))
    implementation(project(Module.api))
    implementation(project(Module.LOCAL))
    implementation(project(Module.CORE_DATA))
    implementation(project(Module.CORE_DOMAIN))

}