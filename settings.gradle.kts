import java.net.URI

include(":feature:profile")


include(":feature:auth")


include(":feature:vehicle")


pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = URI("https://jitpack.io") }
    }
}

rootProject.name = "CarServe"
include(":app")
include(":core:domain")
include(":core:data")
include(":core:common")
include(":feature:service")
include(":feature:common")
