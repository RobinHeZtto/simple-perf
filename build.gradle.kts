buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(BuildInfo.buildGradle)
        classpath(kotlin("gradle-plugin",kotlinVersion))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}