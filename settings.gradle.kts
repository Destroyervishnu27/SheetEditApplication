pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()  // Adding Maven Central as a repository
        gradlePluginPortal()  // Plugin portal for Gradle plugins
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()  // Google repositories
        mavenCentral()  // Maven Central repositories
    }
}

rootProject.name = "SheetEditApplication"  // Name of your project
include(":app")  // Include the app module
