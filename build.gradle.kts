group = "mx.unicaribe"
version = "1.0-SNAPSHOT"


tasks.register("building") {
    dependsOn(gradle.includedBuild("infraestructure").task(":web:micronaut:clean"))
        .dependsOn(gradle.includedBuild("infraestructure").task(":web:micronaut:build"))
    doLast {
        println("ecutando la aplicacion micronaut!!!")
    }
}

tasks.register("build-docker") {
    dependsOn(gradle.includedBuild("infraestructure").task(":web:micronaut:clean"))
        .dependsOn(gradle.includedBuild("infraestructure").task(":web:micronaut:assemble"))
        .dependsOn(gradle.includedBuild("infraestructure").task(":web:micronaut:dockerfile"))
        .dependsOn(gradle.includedBuild("infraestructure").task(":web:micronaut:dockerBuild"))
    doLast {
        println("ecutando la aplicacion micronaut!!!")
    }
}

tasks.register("remove-build-docker") {
    dependsOn(gradle.includedBuild("infraestructure").task(":web:micronaut:dockerfile"))
        .dependsOn(gradle.includedBuild("infraestructure").task(":web:micronaut:dockerBuild"))
    doLast {
        println("ecutando la aplicacion micronaut!!!")
    }
}

tasks.register("migracion") {
    dependsOn(gradle.includedBuild("infraestructure").task(":database:jooq:tables:flywayClean"))
        .dependsOn(gradle.includedBuild("infraestructure").task(":database:jooq:tables:flywayMigrate"))
    doLast {
        println("se ejecuto con exito el script .sql!!!")
    }
}