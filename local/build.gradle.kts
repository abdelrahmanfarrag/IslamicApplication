plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/base-module.gradle")
android {
    namespace = "com.islamic.local"
}
dependencies{

    "kapt"(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)
}