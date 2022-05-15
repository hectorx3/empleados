plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform("mx.unicaribe.ssav2.platform:plugin-platform"))
    /* Kotlin */
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin")
}