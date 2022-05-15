plugins {
    id("java-platform")
}

group = "mx.unicaribe.ssav2.platform"

dependencies {
    constraints {
        //api("io.kotest:kotest-runner-junit5:5.2.1")
        api("io.kotest:kotest-runner-junit5-jvm:4.6.4")
        api("io.mockk:mockk:1.12.3")
    }
}