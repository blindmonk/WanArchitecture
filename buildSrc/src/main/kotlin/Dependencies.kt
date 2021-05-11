object Kotlin {
    const val standardLibrary = "1.3.72"
    const val coroutines = "1.3.9"
}

object AndroidSdk {
    const val min = 21
    const val compile = 29
    const val target = 26
}

object AndroidClient {
    const val appId = "com.fernandocejas.sample"
    const val versionCode = 1
    const val versionName = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object BuildPlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.3"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.standardLibrary}"

    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
}

object ScriptPlugins {
    const val variants = "scripts.variants"
    const val compilation = "scripts.compilation"
}

object Libraries {
    private object Versions {
        const val appCompat = "1.2.0"
        const val constraintLayout = "2.0.2"
        const val recyclerView = "1.1.0"
        const val cardView = "1.0.0"
        const val material = "1.2.0"
        const val lifecycle = "2.2.0"
        const val lifecycleExtensions = "2.1.0"
        const val annotations = "1.1.0"
        const val ktx = "1.3.2"
        const val glide = "4.11.0"
        const val retrofit = "2.9.0"
        const val okHttpLoggingInterceptor = "4.9.0"
        const val dagger = "2.11"
        const val fragmentKtx = "2.2.2"
        const val swipeRefreshLayout = "1.1.0"
        const val recyclerHelper = "3.0.4"
        const val fragment = "1.3.2"
    }

    const val kotlinStdLib             = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Kotlin.standardLibrary}"
    const val kotlinCoroutines         = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"
    const val kotlinCoroutinesAndroid  = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Kotlin.coroutines}"
    const val appCompat                = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout         = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore                  = "androidx.core:core-ktx:${Versions.ktx}"
    const val viewModel                = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData                 = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions      = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val cardView                 = "androidx.cardview:cardview:${Versions.cardView}"
    const val recyclerView             = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val material                 = "com.google.android.material:material:${Versions.material}"
    const val androidAnnotations       = "androidx.annotation:annotation:${Versions.annotations}"
    const val glide                    = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val dagger                   = "com.google.dagger:dagger:${Versions.dagger}"
    const val retrofit                 = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptor}"
    const val daggerCompiler           = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val navigationFragment       = "androidx.navigation:navigation-fragment-ktx:${Versions.fragmentKtx}"
    const val navigationUi             = "androidx.navigation:navigation-ui-ktx:${Versions.fragmentKtx}"
    const val swipeRefreshLayout       = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val fragment                 = "androidx.fragment:fragment-ktx:${Versions.fragment}"

}


object DevLibraries {
    private object Versions {
        const val leakCanary = "2.5"
    }

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
}