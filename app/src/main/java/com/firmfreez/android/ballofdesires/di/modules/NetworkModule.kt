package com.firmfreez.android.ballofdesires.di.modules

import com.firmfreez.android.ballofdesires.network.Api
import com.firmfreez.android.ballofdesires.network.UnsafeOkHttpClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return UnsafeOkHttpClient.getUnsafeOkHttpClient()
    }

    @Singleton
    @Provides
    fun provideApi(gson: Gson, okHttp: OkHttpClient): Api {
        val retrofit = Retrofit.Builder()
                .baseUrl(Api.BACK_URL)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        return retrofit.build().create(Api::class.java)
    }
}