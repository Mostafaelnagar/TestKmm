import org.jetbrains.compose.compose

plugins {
    kotlin(multiplatform)
    id(androidLib)
    id("org.jetbrains.compose") version "1.4.1"
}

android {
    namespace = "com.testkmm.shared_ui"
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

kotlin {
    // 1
    android()
// 2
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }

    sourceSets {
        // 3
        val commonMain by getting {
            dependencies {
                implementation(project(":shared"))

                api(compose.foundation)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.materialIconsExtended)
                api(compose.ui)
                api(compose.uiTooling)
                api(compose.preview)
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {

            }
        }
        val desktopMain by getting
    }

}
