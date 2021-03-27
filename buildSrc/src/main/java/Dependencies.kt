
const val kotlinVersion = "1.4.31"

object BuildInfo {

   const val buildGradle = "com.android.tools.build:gradle:4.1.1"

   const val versionCode = 1
   const val versionName = "1.0.0"
   const val applicationId = "com.he.ztto.perf"
   const val minSdkVersion = 29
   const val compileSdkVersion = 30
   const val targetSdkVersion = 30
   const val buildToolsVersion = "30.0.2"
}


object LibDep {
   const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}"
}

object AndroidxDep {

   const val coreKtx = "androidx.core:core-ktx:1.3.2"
   const val appcompat = "androidx.appcompat:appcompat:1.2.0"
   const val material = "com.google.android.material:material:1.3.0"
   const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"

   const val junit = "junit:junit:4.+"
   const val testExt = "androidx.test.ext:junit:1.1.2"
   const val testEspresso = "androidx.test.espresso:espresso-core:3.3.0"
}