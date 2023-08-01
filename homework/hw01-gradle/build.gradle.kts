plugins {
    id("java")
}

val guava: String by project
val jupiter: String by project
val junit: String by project

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:$junit"))
    testImplementation("org.junit.jupiter:$jupiter")
    implementation("com.google.guava:guava:$guava")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest.attributes["Main-Class"] = "ru.otus.homework.Main"
    val dependencies = configurations
            .runtimeClasspath
            .get()
            .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
