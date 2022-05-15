plugins {
    id("mx.unicaribe.ssav2.micronaut-application")
}

val hikariVersion: String by project

group = "${group}.server-micronaut-application"

println(name)
println(group)

dependencies {
    implementation("mx.unicaribe.ssav2.domain:domain:1.0.0")
    api(project(":database:jooq:app"))
    implementation("mx.unicaribe.ssav2.application:application:1.0.0")

    implementation("com.zaxxer:HikariCP:$hikariVersion")
}

application {
    mainClass.set("mx.unicaribe.ssav2.App")
}


micronaut {
    runtime("netty")
    testRuntime("kotest")
    processing {
        incremental(true)
        annotations("mx.unicaribe.ssav2.*")
    }
}