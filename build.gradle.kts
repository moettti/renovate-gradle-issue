import org.springframework.boot.gradle.plugin.SpringBootPlugin

buildscript {
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:3.0.6")
//		 classpath("org.springframework.boot:spring-boot-gradle-plugin:${ModuleConfiguration.Build.Plugin.SpringBoot.pluginVersion}")
//		 classpath("com.netflix.nebula:nebula-release-plugin:16.0.0")
		 classpath("com.netflix.nebula:nebula-release-plugin:${ModuleConfiguration.Build.Plugin.NebulaRelease.version}")
    }
}

plugins {
	java
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

apply<SpringBootPlugin>()

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

extra["testcontainersVersion"] = "1.18.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-web")
        runtimeOnly("com.h2database:h2:${ModuleConfiguration.Build.Database.h2Version}")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:junit-jupiter")
}

dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
