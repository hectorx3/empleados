import org.jooq.meta.jaxb.ForcedType
import org.jooq.meta.jaxb.Logging
import org.jooq.meta.jaxb.SchemaMappingType

val jooqVersion: String by project
val postgresqlVersion: String by project
val postgresqlHost: String by project
val postgresqlUser: String by project
val postgresqlPassword: String by project

plugins {
    id("mx.unicaribe.ssav2.java-library")
    id("nu.studer.jooq")
    id("org.flywaydb.flyway") version "8.5.10"
}

group = "${group}.jooq-tables-library"

println(name)
println(group)

dependencies {
    implementation("org.postgresql:postgresql:$postgresqlVersion")
    jooqGenerator("org.postgresql:postgresql:$postgresqlVersion")
}

flyway{
    url = "$postgresqlHost"
    user = "$postgresqlUser"
    password = "$postgresqlPassword"
}

jooq {
    version.set("$jooqVersion")
    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(false)
            jooqConfiguration.apply {
                logging = Logging.WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "$postgresqlHost"
                    user = "$postgresqlUser"
                    password = "$postgresqlPassword"
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        schemata.addAll(arrayOf(
                            //SchemaMappingType().apply { inputSchema = "admescolarv2" },
                            //SchemaMappingType().apply { inputSchema = "admsifix" },
                            //SchemaMappingType().apply { inputSchema = "admucaribe" },
                            SchemaMappingType().apply { inputSchema = "public" }
                        ).toList())
                        //inputSchema = "admescolarv2"
                        forcedTypes.addAll(arrayOf(
                            ForcedType()
                                .withName("varchar")
                                .withIncludeExpression(".*")
                                .withIncludeTypes("JSONB?"),
                            ForcedType()
                                .withName("varchar")
                                .withIncludeExpression(".*")
                                .withIncludeTypes("INET")
                        ).toList())
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                    }
                    target.apply {
                        packageName = "schemas"
                        directory = "src/main/java/mx/unicaribe/ssav2/db"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}