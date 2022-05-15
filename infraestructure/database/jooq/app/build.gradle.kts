plugins {
    id("mx.unicaribe.ssav2.kotlin-library")
}

val jooqVersion: String by project
val micronautVersion: String by project
val yamlVersion: String by project
val hikariVersion: String by project
val logbackVersion: String by project
val reactorProjectVersion: String by project

group = "${group}.jooq-app-library"

println(name)
println(group)

dependencies {
    implementation("mx.unicaribe.ssav2.domain:domain:1.0.0")

    implementation(project(":database:jooq:tables"))
    implementation("org.jooq:jooq:$jooqVersion")

    implementation("org.yaml:snakeyaml:$yamlVersion")
    testImplementation("org.yaml:snakeyaml:$yamlVersion")

    api("com.zaxxer:HikariCP:$hikariVersion")

    implementation("io.projectreactor:reactor-core")
    testImplementation("io.projectreactor:reactor-test")

    //implementation("org.postgresql:r2dbc-postgresql:0.9.1.RELEASE")
    implementation("io.r2dbc:r2dbc-postgresql:0.8.12.RELEASE")
    implementation("io.r2dbc:r2dbc-pool:0.9.0.RELEASE")

    implementation("io.micronaut:micronaut-inject:$micronautVersion")
    testImplementation("io.micronaut:micronaut-inject:$micronautVersion")
    annotationProcessor("io.micronaut:micronaut-inject-java:$micronautVersion")
    testAnnotationProcessor("io.micronaut:micronaut-inject-java:$micronautVersion")

    testImplementation("io.kotest:kotest-runner-junit5-jvm")
    testImplementation("io.mockk:mockk")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    constraints {
        implementation("io.projectreactor:reactor-core:$reactorProjectVersion") {
            because("el modulo web -> micronaut, depende de la misma version (evadir misma libreria multiples versiones)")
        }
        testImplementation("io.projectreactor:reactor-test:$reactorProjectVersion")
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