plugins {
    kotlin("multiplatform") version "1.3.72"
}
group = "org.example"
version = "1.0-SNAPSHOT"
repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    mavenCentral()
}

kotlin {
    js { nodejs() }
    jvm {}
    mingwX64 {}
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        //TODO: these arent run by default.. gotta do some convoluted shit https://discuss.kotlinlang.org/t/not-able-to-run-unit-tests-on-kotlin-multiplatform-project/15779
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
            }
        }
        //TODO: mingwX64('mingw').compilations.main.defaultSourceSet { /* ... */ }
        val mingwX64Test by getting { }
    }
}