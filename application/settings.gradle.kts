rootProject.name = "application"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    includeBuild("../build-logic")
}

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

include("otro")