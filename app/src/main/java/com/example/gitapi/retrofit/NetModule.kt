package com.example.daggerex

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.gitapi.retrofit.GithubAPI
import dagger.Module
import dagger.Provides
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import com.google.gson.Gson
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetModule(private val baseUrl: String) {

    @Provides
    @Singleton
    internal fun provideApi(retrofit: Retrofit): GithubAPI = retrofit.create(GithubAPI::class.java)

    @Provides
    internal fun provideHttpClient(cache: Cache, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient().newBuilder()
                .connectTimeout((30000 * 6).toLong(), TimeUnit.MILLISECONDS)
                .readTimeout((30000 * 6).toLong(), TimeUnit.MILLISECONDS)
                .writeTimeout((30000 * 6).toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build()
    }

    @Provides
    internal fun loggInterception():HttpLoggingInterceptor{
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }


    @Provides
    internal fun provideCache(application: Application): Cache {
        val cacheSize = 1024 * 1024 * 10
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    internal fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }
}