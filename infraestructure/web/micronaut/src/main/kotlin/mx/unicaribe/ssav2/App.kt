package mx.unicaribe.ssav2

import io.micronaut.runtime.Micronaut

object App {
    val enviroment: String = "dev"

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .args(*args)
            .packages("mx.unicaribe.ssav2")
            //.defaultEnvironments(enviroment)
            .eagerInitConfiguration(true)
            .start()
    }
}