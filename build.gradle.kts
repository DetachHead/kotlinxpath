import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.4.0"
    id("org.jetbrains.dokka") version "1.4.20-dev-10"
    id("org.jlleitschuh.gradle.ktlint") version "10.1.0"
    `maven-publish`
}

group = "io.github.detachhead"
version = "2.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.useIR = true
}

kotlin {
    explicitApi()
    js { nodejs() }
    jvm {}
    mingwX64 {}
    sourceSets {
        @Suppress("unused_variable")
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        @Suppress("unused_variable")
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }

        @Suppress("unused_variable")
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-js"))
            }
        }

        @Suppress("unused_variable")
        val mingwX64Test by getting { }
    }
}

val publishToGithubPages: Task by tasks.creating {
    group = "publishing"
    doFirst {
        val publishLocation = File(publishing.repositories.mavenLocal().url)
            .resolve("${project.group.toString().replace('.', '/')}/${project.name}")
        if (!version.toString()
            .endsWith("-SNAPSHOT") &&
            publishLocation.list()?.contains(version) == true
        )
            error("$version has already been published")
    }
    finalizedBy(tasks.publishToMavenLocal)
}
