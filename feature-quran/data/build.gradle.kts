plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/base-module.gradle")
android {
    namespace = "com.islamic.quran_data"
}
dependencies {
    implementation(project(Module.api))
    implementation(Retrofit.RETROFIT_CONVERTER_GSON)
    implementation(project(Module.LOCAL))
    implementation(project(Module.CORE_DATA))
    implementation(project(Module.CORE_DOMAIN))
    implementation(project(Module.FEATURE_QURAN_DOMAIN))
}