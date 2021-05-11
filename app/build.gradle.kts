plugins {
    // 系统插件
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.kotlinAndroidExtensions)

    // 内部插件
    id(ScriptPlugins.variants)
    id(ScriptPlugins.compilation)
}


dependencies {
    //普通依赖
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.kotlinCoroutines)
    implementation(Libraries.kotlinCoroutinesAndroid)
    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.viewModel)
    implementation(Libraries.liveData)
    implementation(Libraries.lifecycleExtensions)
    implementation(Libraries.cardView)
    implementation(Libraries.recyclerView)
    implementation(Libraries.material)
    implementation(Libraries.androidAnnotations)
    implementation(Libraries.glide)
    implementation(Libraries.dagger)
    implementation(Libraries.retrofit)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)
    implementation(Libraries.okHttpLoggingInterceptor)
    implementation(Libraries.swipeRefreshLayout)
    implementation(Libraries.fragment)

    //开发依赖
    debugImplementation(DevLibraries.leakCanary)

    //编译时依赖
    kapt(Libraries.daggerCompiler)
}
