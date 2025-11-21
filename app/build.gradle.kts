val checkstyleVersion = "12.1.1"
val picocliVersion = "4.7.7"
val jacksonDatabindVersion = "2.18.2"
val jacksonYamlVersion = "2.19.0"
val lombokVersion = "1.18.42"
val junitBomVersion = "5.10.0"

plugins {
    application
    jacoco
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
    toolVersion = checkstyleVersion
    configFile = file("config/checkstyle/checkstyle.xml")
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
    implementation("com.puppycrawl.tools:checkstyle:$checkstyleVersion")

    implementation("info.picocli:picocli:$picocliVersion")

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonDatabindVersion")

    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonYamlVersion")

    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation(platform("org.junit:junit-bom:$junitBomVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco/jacocoHtml"))
    }
}

tasks.withType(Checkstyle::class) {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}