plugins {
    application
    id("checkstyle")
    id("com.github.ben-manes.versions") version "0.53.0"
    id("org.sonarqube") version "7.0.1.6134"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("hexlet.code.App")
}

checkstyle {
    toolVersion = "12.1.1"
    configFile = file("config/checkstyle/google_checks.xml")
    maxWarnings = 0
}

sonar {
    properties {
        property("sonar.projectKey", "Feoor_java-project-71")
        property("sonar.organization", "feoor")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // checkstyle
    implementation("com.puppycrawl.tools:checkstyle:12.1.1")

    implementation("info.picocli:picocli:4.7.7")

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")

    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType(Checkstyle::class) {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}