plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("plugin.jpa") version "1.9.25"
	jacoco
}

group = "com.bbgk"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")

	// TEST
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation ("org.mockito.kotlin:mockito-kotlin:5.4.0") // Test 진행 시 any(), ArgumentCaptor에 null 문제가 발생하여 추가
	// Kotlin 버전의 Mockito, https://github.com/mockito/mockito-kotlin?tab=readme-ov-file
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// JDSL
	val jdslVersion = "3.5.3"
	implementation("com.linecorp.kotlin-jdsl:jpql-dsl:$jdslVersion")
	implementation("com.linecorp.kotlin-jdsl:jpql-render:$jdslVersion")
	implementation("com.linecorp.kotlin-jdsl:spring-data-jpa-support:$jdslVersion")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jacoco {
	toolVersion = "0.8.12" // JaCoCo 버전 설정
	// reportsDirectory = layout.buildDirectory.dir("reports/jacoco") // 기본 보고서 생성 디렉토리, Gradle Default 값
}

tasks.test {
	finalizedBy(tasks.jacocoTestReport) // 테스트 실행 후에 항상 보고서가 생성됨.
}
tasks.jacocoTestReport {
	dependsOn(tasks.test) // 보고서 생성 전에 테스트 실행이 선행되어야 함.
}
tasks.jacocoTestReport {
	reports {
		html.required = true // HTML 리포트 활성화
		xml.required = false // 출력 형식 설정
		csv.required = false // 출력 형식 설정
		html.outputLocation = layout.buildDirectory.dir("jacocoHtml") // HTML jacoco 보고서 생성 디렉토리
	}

	classDirectories.setFrom(files(classDirectories.files.map {
		fileTree(it) {
			setExcludes(listOf(
				"**/domain/DataInitializer.class",
			))
		}
	}))
}
tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			limit {
				minimum = "1.0".toBigDecimal() // 최소 커버리지 설정
			}
		}
	}

	classDirectories.setFrom(files(classDirectories.files.map {
		fileTree(it) {
			setExcludes(listOf(
				"**/domain/DataInitializer.class",
			))
		}
	}))
}
