dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

includeBuild("../platforms")

rootProject.name = "build-logic"
include("commons")
include("kotlin-library")
include("micronaut-application")
include("java-library")