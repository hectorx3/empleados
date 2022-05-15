pluginManagement {
    val jooqPluginVersion: String by settings
    plugins {
        id("nu.studer.jooq") version "$jooqPluginVersion"
    }
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

includeBuild("../platforms")

rootProject.name = "infraestructure"
include("database:jooq:app")
include("database:jooq:tables")
include("web:micronaut")
//include("web:spark")
//include("web:spring-boot")