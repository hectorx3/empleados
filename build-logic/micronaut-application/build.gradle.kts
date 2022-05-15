plugins {
    `kotlin-dsl`
}

println(name)
println(group)

dependencies {
    implementation(project(":commons"))

    implementation(platform("mx.unicaribe.ssav2.platform:plugin-platform"))
    /* Kotlin */
    implementation("org.jetbrains.kotlin.kapt:org.jetbrains.kotlin.kapt.gradle.plugin")
    implementation("org.jetbrains.kotlin.plugin.allopen:org.jetbrains.kotlin.plugin.allopen.gradle.plugin")
    /* Micronaut Framework */
    implementation("io.micronaut.application:io.micronaut.application.gradle.plugin")
    implementation("com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin")
}