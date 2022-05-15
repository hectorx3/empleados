plugins {
    id("java")
}

group = "mx.unicaribe.ssav2"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    testImplementation(platform("mx.unicaribe.ssav2.platform:test-platform"))
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}