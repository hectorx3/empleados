plugins {
    id("mx.unicaribe.ssav2.commons")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("com.github.johnrengelman.shadow")
    id("io.micronaut.application")
}

dependencies {
    implementation("io.micronaut:micronaut-http-client")
    testImplementation("io.micronaut:micronaut-http-client")

    implementation("io.micronaut:micronaut-inject")
    testImplementation("io.micronaut:micronaut-inject")
    annotationProcessor("io.micronaut:micronaut-inject-java")
    testAnnotationProcessor("io.micronaut:micronaut-inject-java")
    kapt("io.micronaut:micronaut-inject-java")
    kaptTest("io.micronaut:micronaut-inject-java")


    implementation("io.micronaut.reactor:micronaut-reactor")
    implementation("io.micronaut.reactor:micronaut-reactor-http-client")

    testImplementation(platform("mx.unicaribe.ssav2.platform:test-platform"))
    testImplementation("io.mockk:mockk")

    constraints {
        implementation("io.projectreactor:reactor-core:3.4.11") {
            because("el modulo de database -> jooq, depende de la misma version, asi no se tendria la misma libreria multiples veces")
        }
    }
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}