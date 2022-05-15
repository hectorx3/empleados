dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
    includeBuild("../build-logic")
}

rootProject.name = "domain"