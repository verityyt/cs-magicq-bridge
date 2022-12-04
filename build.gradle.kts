import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.DuplicateFormatFlagsException

plugins {
    java
    kotlin("jvm") version "1.7.21"
}

group = "dev.verity"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("ch.bildspur:artnet4j:0.6.2")
    implementation("com.google.code.gson:gson:2.10")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_15
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    jar {
        archiveName = "CSMagicQBridge.jar"

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(sourceSets.main.get().output)
        dependsOn(configurations.runtimeClasspath)

        from({
            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })

        manifest {
            attributes["Main-Class"] = "CSMagicQBridge"
        }

        exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
    }
}

tasks.register("export") {
    group = "build"
    description = "Exports the project as jar file"

    dependsOn("jar")
}