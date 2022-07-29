package ru.zar1official.moviesapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.zar1official.moviesapp.BuildConfig
import ru.zar1official.moviesapp.data.DataConstants
import ru.zar1official.moviesapp.data.MoviesService
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor {
            val originalRequest: Request = it.request()
            val originalHttpUrl: HttpUrl = originalRequest.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(DataConstants.API_KEY_QUERY, BuildConfig.API_KEY)
                .build()

            val requestBuilder: Request.Builder = originalRequest.newBuilder()
                .url(url)

            val newRequest: Request = requestBuilder.build()
            it.proceed(newRequest)
        }
    }
}