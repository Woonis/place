dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // query dsl annotaion processing
    implementation("com.querydsl:querydsl-core:${Versions.queryDsl}")
    annotationProcessor("com.querydsl:querydsl-apt:${Versions.queryDsl}:jakarta")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api:3.1.0")
}
