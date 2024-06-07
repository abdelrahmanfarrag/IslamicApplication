plugins {
    `android-library`
    `kotlin-android`
}
apply(from = "$rootDir/base-module.gradle")
apply(from = "$rootDir/jacoco.gradle")
android {
    namespace = "com.islamic.api"
}
dependencies{
    implementation(Retrofit.retrofit)
    implementation(Retrofit.RETROFIT_CONVERTER_GSON)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.okHttp)

}