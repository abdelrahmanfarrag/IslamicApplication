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
