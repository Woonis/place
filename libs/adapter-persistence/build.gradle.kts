
dependencies {
    implementation(project(":libs:application"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    implementation("org.flywaydb:flyway-core")
    implementation("com.querydsl:querydsl-core:${Versions.queryDsl}")
    implementation("com.querydsl:querydsl-jpa:${Versions.queryDsl}:jakarta")

    runtimeOnly("com.h2database:h2")
}
