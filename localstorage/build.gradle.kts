plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "pl.slaszu.localstorage"
    compileSdk = 36

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])
                groupId = "pl.slaszu"
                artifactId = "localstorage"
                version = "1.0.0"
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.datastore)
    implementation(libs.kotlinx.serialization.json)
}