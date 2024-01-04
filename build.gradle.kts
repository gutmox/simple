plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.vertx:vertx-pg-client:4.5.1")
    implementation("com.ongres.scram:client:2.1")
    implementation("com.github.f4b6a3:ulid-creator:5.2.2")
    testImplementation("org.assertj:assertj-core:3.25.0")
    testImplementation("org.awaitility:awaitility:4.2.0")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("5.9.3")
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("com.gutmox.server.Main")
}
