plugins {
    id("mx.unicaribe.ssav2.commons")
    id("org.jetbrains.kotlin.jvm")
    id("java-library")
}

dependencies {
    //implementation(kotlin("stdlib"))
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("jakarta.inject:jakarta.inject-api")

    constraints {
        implementation("jakarta.inject:jakarta.inject-api:2.0.1") {
            because("misma que usa micronaut, depende de la misma version, asi no se tendria la misma libreria multiples veces")
        }
    }
}
