buildscript {
    repositories {
        maven(url = "./repo-local")
        google()
        jcenter()
    }
    dependencies {
        classpath(BuildInfo.buildGradle)
        classpath(kotlin("gradle-plugin", kotlinVersion))
        classpath("com.he.ztto:simple-perf:1.0.0-SNAPSHOT")
    }
}

allprojects {
    repositories {
        maven(url = "./repo-local")
        google()
        jcenter()
    }
}