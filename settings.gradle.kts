pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Islamic Application"
include(":app")
include(":api")
include(":feature-home")
include(":feature-home:data")
include(":feature-home:domain")
include(":feature-home:presentation")
include(":core")
include(":core:data")
include(":core:domain")
include(":core:presentation")
include(":local")
include(":services")
include(":feature-quran")
include(":feature-quran:data")
include(":feature-quran:domain")
include(":feature-quran:presentation")
include(":feature-surrah")
include(":feature-surrah:data")
include(":feature-surrah:domain")
include(":feature-surrah:presentation")
