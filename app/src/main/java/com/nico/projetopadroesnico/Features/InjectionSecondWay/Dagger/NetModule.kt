package com.nico.projetopadroesnico.Features.InjectionSecondWay.Dagger

import dagger.Module
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import com.google.gson.Gson
import javax.inject.Singleton
import dagger.Provides
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import android.app.Application
import android.preference.PreferenceManager
import android.content.SharedPreferences
import com.nico.projetopadroesnico.Features.InjectionSecondWay.Presenter.OkHttpPresenter
import com.nico.projetopadroesnico.Features.InjectionSecondWay.Presenter.OkHttpPresenterImpl
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named


@Module
class NetModule(private val baseUrl: String) {

    // Dagger will only look for methods annotated with @Provides
    @Singleton
    @Provides
    fun providesSharedPreferences(application: Application):
            // Application reference must come from AppModule.class
            SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Singleton
    @Provides
    fun provideOkHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    @Named("cached")
    fun provideOkHttpClientWithCache(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(cache)
        return client.build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpPresenter = OkHttpPresenterImpl()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
    }
}