plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":perf-annotations"))
    implementation(LibDep.kotlinStdlib)
    implementation(LibDep.autoService)
}