plugins {
    id("com.android.application")
    id ("kotlin-android")
    id ("simple-perf")
}

android {
    compileSdkVersion(BuildInfo.compileSdkVersion)
    buildToolsVersion(BuildInfo.buildToolsVersion)

    defaultConfig {
        applicationId(BuildInfo.applicationId)
        minSdkVersion(BuildInfo.minSdkVersion)
        targetSdkVersion(BuildInfo.targetSdkVersion)
        versionCode(BuildInfo.versionCode)
        versionName(BuildInfo.versionName)
    }

    signingConfigs {
        create("release") {
            storeFile = file("../key.jks")
            storePassword = "android"
            keyAlias = "android"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }


    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(LibDep.kotlinStdlib)
    implementation(AndroidxDep.coreKtx)
    implementation(AndroidxDep.appcompat)
    implementation(AndroidxDep.material)
    implementation(AndroidxDep.constraintLayout)
    testImplementation(AndroidxDep.junit)
    androidTestImplementation(AndroidxDep.testExt)
    androidTestImplementation(AndroidxDep.testEspresso)
}