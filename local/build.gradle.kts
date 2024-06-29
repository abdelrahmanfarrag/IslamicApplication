plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/base-module.gradle")
android {
    namespace = "com.islamic.local"
}
dependencies{
    implementation("androidx.appcompat:appcompat:1.7.0")
    "kapt"(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Retrofit.RETROFIT_CONVERTER_GSON)
    implementation(Room.roomRuntime)
}