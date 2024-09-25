plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.spring") version "2.0.20"
    kotlin("plugin.jpa") version "2.0.20"
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

group = "com.fbxmtjqj"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("com.h2database:h2")
    implementation("com.mysql:mysql-connector-j:8.4.0")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")


    implementation("com.navercorp.spring:spring-boot-starter-batch-plus-kotlin:1.1.0")
    // kotlin jdsl
    implementation("com.linecorp.kotlin-jdsl:jpql-dsl:3.5.1")
    implementation("com.linecorp.kotlin-jdsl:jpql-render:3.5.1")
    implementation("com.linecorp.kotlin-jdsl:spring-batch-support:3.5.1")
    implementation("com.linecorp.kotlin-jdsl:spring-data-jpa-support:3.5.1")

}

configurations.all {
    exclude("org.springframework.boot", "spring-boot-starter-logging")
    exclude("commons-logging", "commons-logging")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}
