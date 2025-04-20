rootProject.name = "Lexivo"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

// Client
include(":userApp")
include(":adminApp")
// Shared
include(":shared")
// Server
include(":server")
include(":server:mlgate")
include(":server:feedback")
include(":server:auth")
include(":server:history")
include(":server:review")
include(":server:notification")
include(":server:team")
include(":server:user")
