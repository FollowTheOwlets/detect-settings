import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("jvm") version "1.6.0"
    id("checkstyle")
    id("io.gitlab.arturbosch.detekt") version("1.19.0")
}

group = "ru.fedorichev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        xml.outputLocation.set(file("build/reports/detekt.xml"))
        html.required.set(true)
        html.outputLocation.set(file("build/reports/detekt.html"))
    }
}

detekt {
    toolVersion = "1.19.0"
    source.setFrom("src/main/kotlin")
    config.setFrom("src/main/resources/detekt/config.yml")
    basePath = projectDir.absolutePath
    autoCorrect = true
}

tasks.test {
    useJUnitPlatform()
}