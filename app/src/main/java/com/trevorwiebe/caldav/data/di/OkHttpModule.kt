package com.trevorwiebe.caldav.data.di

import com.trevorwiebe.caldav.BuildConfig
import com.trevorwiebe.caldav.data.CalDavApi
import com.trevorwiebe.caldav.data.CalDavApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return if(BuildConfig.DEBUG){
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }else{
            OkHttpClient.Builder()
                .build()
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpRequest(): Request.Builder{
        return Request.Builder()
    }

    @Singleton
    @Provides
    fun provideApiHelper(
        request: Request.Builder,
        okHttpClient: OkHttpClient
    ): CalDavApi {
        return CalDavApiImpl(request, okHttpClient)
    }

}