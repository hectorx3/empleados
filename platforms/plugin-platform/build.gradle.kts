plugins {
    id("java-platform")
}

group = "mx.unicaribe.ssav2.platform"

dependencies {
    constraints {
        /* Kotlin */
        api("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:1.5.31")
        api("org.jetbrains.kotlin.kapt:org.jetbrains.kotlin.kapt.gradle.plugin:1.5.31")
        api("org.jetbrains.kotlin.plugin.allopen:org.jetbrains.kotlin.plugin.allopen.gradle.plugin:1.5.31")
        /* Micronaut Framework */
        api("io.micronaut.application:io.micronaut.application.gradle.plugin:3.3.0")
        api("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin:7.1.2")
    }
}