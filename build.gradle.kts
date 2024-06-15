plugins {
    id("java")
    id("org.springframework.boot") version "3.1.0"
    id("com.google.cloud.tools.jib") version "3.1.0"
}

group = "sentinelguard"
version = "1.0-EurekaServer-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:4.1.2")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.1.5")
    implementation("io.micrometer:micrometer-tracing-bridge-brave:1.0.2")
    implementation("io.zipkin.reporter2:zipkin-reporter-brave:2.16.3")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.1.0")
}

jib {
    from {
        image = "eclipse-temurin:17"
    }
    to {
        image = "docker.io/unkindledone/unkindledone-repository"
        tags = setOf(project.version.toString())
        auth {
            username = findProperty("dockerHubUsername") as String?
            password = findProperty("dockerHubPassword") as String?
        }
    }
    container {
        mainClass = "eurekaserver.EurekaServerApplication"
    }
}