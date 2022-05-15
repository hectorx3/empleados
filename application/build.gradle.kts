plugins {
    id("mx.unicaribe.ssav2.kotlin-library")
}

group = "${group}.application"
version = "1.0.0"

dependencies {
    implementation("mx.unicaribe.ssav2.domain:domain:1.0.0")
}