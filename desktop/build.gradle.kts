import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

// 1
plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.4.1"

}

// 2
group = "com.example.testkmm"
version = "1.0.0"

// 3
kotlin {
    // 1
    jvm {
        withJava()
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }
// 2
    sourceSets {
        val jvmMain by getting {
//            // 3
            kotlin.srcDirs("src/jvmMain/kotlin")
            dependencies {
//                // 4
                implementation(compose.desktop.currentOs)
////                // 5
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
                api(compose.materialIconsExtended)

                implementation(Deps.napier)
                // Coroutines
                implementation(Deps.Coroutines.common)

                // 6
                implementation(project(":shared"))
                implementation(project(":shared-ui"))
            }
        }
    }

}

// 1
compose.desktop {
    // 2
    application {
        // 3
        mainClass = "MainKt"
        // 4
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "testkmm"
            macOS {
                bundleID = "com.example.testkmm"
            }
        }
    }
}
