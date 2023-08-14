dependencies {
    implementation(project(":libs:application"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    runtimeOnly(project(":libs:adapter-http"))
    runtimeOnly(project(":libs:adapter-persistence"))
}

val appMainClassName = "sample.wooni.place.PlaceApiApplication"
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set(appMainClassName)
    archiveClassifier.set("boot")
}