plugins {
    id("com.android.application") version "8.7.3" apply false
}
configurations.all {
    resolutionStrategy {
        force("org.jetbrains.kotlin:kotlin-stdlib:1.8.22")
        force("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22")
        force("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22")
    }
}
plugins {
    id("com.android.application")
}

android {
    namespace = "com.pocoisland.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pocoisland.app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
}
