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
