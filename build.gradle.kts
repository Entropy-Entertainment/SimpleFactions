plugins {
    id("java-library")
    id("com.gradleup.shadow") version "9.2.2"
}

group = "net.entropyentertainment.simplefactions"
version = "3.0.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

dependencies {
    compileOnly(files("libs/HytaleServer.jar"))
    implementation("org.xerial:sqlite-jdbc:3.42.0.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("junit:junit:4.13.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    enabled = false
}

tasks.shadowJar {
    archiveClassifier.set("")
    mergeServiceFiles()
}