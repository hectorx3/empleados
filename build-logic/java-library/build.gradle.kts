plugins {
    `kotlin-dsl`
}

println(name)
println(group)

dependencies {
    implementation(project(":commons"))
    implementation(platform("mx.unicaribe.ssav2.platform:plugin-platform"))
}