import org.gradle.kotlin.dsl.test
import org.gradle.kotlin.dsl.testImplementation
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
//    kotlin("jvm") version "2.0.20-Beta2"
//    kotlin("jvm") version "2.0.20"
//    kotlin("multiplatform") version "2.0.0"
//    kotlin("jvm") version "1.9.24"
    kotlin("jvm") version "2.1.0-RC"
//    kotlin("plugin.serialization") version "2.0.0"
//    id("org.jetbrains.kotlinx.rpc.plugin") version "0.2.1"
}


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { setUrl("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev") }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC.2")
//    implementation("org.jetbrains.exposed:exposed-core:0.50.1")
//    implementation("org.jetbrains.exposed:exposed-crypt:0.50.1")
//    implementation("org.jetbrains.exposed:exposed-dao:0.50.1")
//    implementation("org.jetbrains.exposed:exposed-jdbc:0.50.1")

//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
//    implementation(kotlin("stdlib-jdk8"))

}

//tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
//    kotlinOptions.freeCompilerArgs = listOf("-Xcontext-receivers")
//}

//tasks.test {
//    useJUnitPlatform()
//}
kotlin {
    jvmToolchain(19)



}

//tasks.named("compileKotlin", org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask::class.java) {
//    compilerOptions {
//        freeCompilerArgs.add("-Xexport-kdoc")
//    }
//}

