plugins {
    `android-library`
    `kotlin-android`

}
apply(from = "$rootDir/base-module.gradle")
android {
    namespace = "com.islamic.services"
}
dependencies{
    implementation(WorkManager.WORK_MANAGER)
    implementation(WorkManager.HILT_WORKER)
    implementation(project(Module.home_domain))
    implementation(project(Module.CORE_DOMAIN))
    "kapt"("androidx.hilt:hilt-compiler:1.2.0")

}