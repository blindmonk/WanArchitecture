object Dependencies {
    const val AndroidBuildTools = "com.android.tools.build:gradle:4.1.3"
}

plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
}

dependencies {
    implementation(Dependencies.AndroidBuildTools)
}