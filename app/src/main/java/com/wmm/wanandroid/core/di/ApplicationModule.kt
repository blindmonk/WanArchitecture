package com.wmm.wanandroid.core.di

import android.content.Context
import com.wmm.wanandroid.AndroidApplication
import com.wmm.wanandroid.BuildConfig
import com.wmm.wanandroid.features.home.model.ArticleRepository
import com.wmm.wanandroid.features.home.model.HomeRepository
import com.wmm.wanandroid.features.project.model.ProjectRepository
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {


    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val cache = Cache(File(application.cacheDir, "WanCacheFile"), 1024 * 1024 * 50.toLong())
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideHomeRepository(dataSource: HomeRepository.Network): HomeRepository = dataSource

    @Provides
    @Singleton
    fun provideProjectRepository(dataSource: ProjectRepository.Network): ProjectRepository = dataSource

    @Provides
    @Singleton
    fun provideArticleRepository(dataSource: ArticleRepository.Network): ArticleRepository = dataSource

}
