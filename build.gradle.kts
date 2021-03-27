buildscript {
    repositories {
        maven(url = "./repo-local")
        google()
        jcenter()
    }
    dependencies {
        classpath(BuildInfo.buildGradle)
        classpath(kotlin("gradle-plugin", kotlinVersion))
        classpath(SimplePerf.simplePerfPlugin)
    }
}

allprojects {
    repositories {
        maven(url = "./repo-local")
        google()
        jcenter()
    }
}