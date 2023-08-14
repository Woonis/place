dependencies {
    implementation(project(":libs:application"))

    implementation("org.springframework.boot:spring-boot-starter-json")
    implementation("org.springframework:spring-web")

    // circuit breaker
    implementation("io.github.resilience4j:resilience4j-spring-boot3")
    implementation("org.springframework.boot:spring-boot-starter-aop")
}
